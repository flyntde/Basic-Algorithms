#!/usr/bin/env python

import os
import random
import copy
import time

def read_graph(content):
    graph = {}
    
    for line in content:
        l = [int(x) for x in line.split()]
        graph[l[0]] = l[1:]

    return graph

def get_edges(graph):
    edges = set()
    for u in graph:
        for v in graph[u]:
            edges.add(tuple(sorted([u,v]))) # removes duplicates
    
    return [[x,y] for (x,y) in edges]

def contract(nodes, edges):
    while len(nodes) > 2:
        # pick a remaining edge (u,v) uniformly at random
        u,v = edges[random.randint(0, len(edges) - 1)]
        
        # remove this edge
        edges.remove([u,v])

        # merge u and v into the single vertex u
        nodes.remove(v)

        # replace v with u in edges
        for edge in edges:
            if edge[0] == v: edge[0] = u
            if edge[1] == v: edge[1] = u

        # remove self-loops
        edges = list(filter(([u,u]).__ne__, edges))

    return len(edges)

def main():
    with open('kargerMinCut.txt', 'r') as f:
        content = f.readlines()

    graph = read_graph(content)
    #graph = {1:[2,3,4], 2:[1,3], 3:[1,2,4], 4:[1,3]}
    nodes = list(graph.keys())
    edges = get_edges(graph)
    min_cut = []
    start_time = time.time()
    for _ in range(1000):
        min_cut.append(contract(nodes.copy(), copy.deepcopy(edges)))
        
    end_time=time.time()
    print(end_time-start_time)
    print(min(min_cut))

if __name__ == "__main__":
    main()