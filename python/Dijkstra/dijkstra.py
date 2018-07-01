#!/usr/bin/env python

def read_graph(filename):
    g = {}

    with open(filename, 'r') as f:
        content = f.readlines()

    for values in (line.split() for line in content):
        node = int(values[0])
        g[node] = []
        for edge in values[1:]:
            neighbor,weight = (int(x) for x in edge.split(','))
            g[node].append((neighbor, weight))

    return g


def dijkstra(g, start):
    unexplored = set([x for x in range(1,len(g)+1)])
    explored = set()
    unexplored.remove(start)
    explored.add(start)
    shortest = [int(1e6)]*(len(g) + 1)
    shortest[start] = 0


    while len(unexplored):
        # among all edges (v, w) with v in explored and w in
        # unexplored, pick the one that minimizes shortest[v] + length(v,w)
        minlength = int(1e6)

        for v in explored:
            for w,length in g[v]:
                if w in explored: continue

                if shortest[v] + length < minlength: 
                    minlength = shortest[v] + length
                    best_edge = (v, w, minlength)

        unexplored.remove(best_edge[1])
        explored.add(best_edge[1])
        shortest[best_edge[1]] = minlength
    
    return shortest

def main():
    g = read_graph('dijkstraData.txt')
    paths = dijkstra(g, 1)
    for i in range(1,len(paths)):
        print(i,paths[i])


if __name__ == '__main__':

    main()