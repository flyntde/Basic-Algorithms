#!/usr/bin/env python

import os
from pprint import pprint

def read_graph(g, g_rev, filename):
    with open(filename, 'r') as f:
        content = f.readlines()

    for line in content:
        u,v = [int(x) for x in line.split()]
        if not g.get(u): g[u] = []
        if not g_rev.get(v): g_rev[v] = []
        g[u].append(v)
        g_rev[v].append(u)

    max_label = max(g.keys())
    for i in range(1, max_label+1):
        if not g.get(i): g[i] = []
        if not g_rev.get(i): g_rev[i] = []

    assert len(g) == max_label
    assert len(g_rev) == max_label


def dfs_loop(g, order):
    global s
    for node in order:
        if not explored[node]:
            s = node
            dfs(g, node)

def dfs(g, u):
    global t
    explored[u] = True
    leader[u] = s

    for v in g[u]:
        if not explored[v]:
            dfs(g,v)

    t += 1
    finish[u] = t

def get_order(finish):
    n = len(finish) - 1
    order = [0] * (n + 1)
    for i in range(1, n + 1):
        order[finish[i]] = i
    order.reverse()
    return order[:n]

def main():
    global leader, finish, explored
    g = {}
    g_rev = {}

    read_graph(g, g_rev, 'SCC.txt')
    n = len(g)
    finish = [0] * (n + 1)
    explored = [False] * (n + 1)
    order = [x for x in range(len(g) ,0,-1)]
    dfs_loop(g_rev, order)
    order = get_order(finish)
    leader.clear()
    explored = [False] * (n + 1)

    dfs_loop(g, order)

    inv_leader = {}
    for k,v in leader.items():
        if not inv_leader.get(v): inv_leader[v] = []
        inv_leader[v].append(k)


    inv_leader_len = {}
    for k, v in inv_leader.items():
        inv_leader_len[k] = len(v)

    biggest = sorted(inv_leader_len.items(), key = lambda x: x[1], reverse=True)
    print(biggest[:5])
        


if __name__ == '__main__':
    leader = {}
    finish = []
    explored = []
    s = 0
    t = 0
    main()