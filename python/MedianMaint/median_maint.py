#!/usr/bin/env python

import os
import heapq

class MaxHeapObj(object):
  def __init__(self,val): self.val = val
  def __lt__(self,other): return self.val > other.val
  def __eq__(self,other): return self.val == other.val
  def __str__(self): return str(self.val)

class MinHeap(object):
  def __init__(self): self.h = []
  def heappush(self,x): heapq.heappush(self.h,x)
  def heappop(self): return heapq.heappop(self.h)
  def __getitem__(self,i): return self.h[i]
  def __len__(self): return len(self.h)

class MaxHeap(MinHeap):
  def heappush(self,x): heapq.heappush(self.h,MaxHeapObj(x))
  def heappop(self): return heapq.heappop(self.h).val
  def __getitem__(self,i): return self.h[i].val

def findmedian(odd, heaplow, heaphigh):
    if (not odd): return heaplow[0]

    if len(heaplow) > len(heaphigh): return heaplow[0]

    return heaphigh[0]

def rebalance(heaplow, heaphigh):
    if len(heaplow) == len(heaphigh): return

    if len(heaplow) > len(heaphigh):
        heaphigh.heappush(heaplow.heappop())
        return

    heaplow.heappush(heaphigh.heappop())

def main():
    heaplow = MaxHeap()
    heaphigh = MinHeap()

    filename = 'Median.txt'
    with open(filename, 'r') as f:
        content = f.readlines()

    i = int(content[0])
    heaplow.heappush(i)
    odd = True
    mediansum = 0
    mediansum += findmedian(odd, heaplow, heaphigh)

    for line in content[1:]:
        i = int(line)
        if i < heaplow[0]: 
            heaplow.heappush(i)
        else:
            heaphigh.heappush(i)

        odd = not odd
        if not odd: rebalance(heaplow, heaphigh)
        
        mediansum += findmedian(odd, heaplow, heaphigh)

    print(mediansum % 10000)
    print(heaplow[0], heaphigh[0])

if __name__ == '__main__':

    main()