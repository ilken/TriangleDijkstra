TriangleDijkstra
================

Find the shortest path between top vertex and the bottom edge of the equilateral triangle.


Example Triangle.txt

7
6 3
3 8 5
11 2 10 9

A path through the triangle is a sequence of adjacent nodes, one from each row, starting from the top.
For exampls: 7 -> 6 -> 3 -> 11 is a path down the left hand edge of the triangle.

A minimal path is one where the sum of the values in its nodes is no greater than for any other path through the triangle.
In this case 7 + 6 + 3 + 2 = 18

**getShortestPathTo() method uses Dijkstra algorithm
