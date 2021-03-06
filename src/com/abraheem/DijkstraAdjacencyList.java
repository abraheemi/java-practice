package com.abraheem;

import java.util.*;

public class DijkstraAdjacencyList {
    private final int vertices; // Number of vertices
    private final LinkedList<Node>[] adj; // Adjacency List
    private Set<Integer> visited;
    PriorityQueue<Node> pQueue;
    int[] dist;

    public DijkstraAdjacencyList(int numOfVertices) {
        vertices = numOfVertices;
        dist = new int[numOfVertices];
        visited = new HashSet<Integer>();
        pQueue = new PriorityQueue<Node>(numOfVertices, new Node());

        adj = new LinkedList[numOfVertices];
        for(int i=0; i<numOfVertices; ++i){
            adj[i] = new LinkedList<Node>();
        }
    }

    public void addEdge(int vSrc, int vDst, int weight){
        adj[vSrc].add(new Node(vDst, weight));
    }

    public int[] shortestPath(int start){
        // All nodes start with dist = infinity from start node
        for(int i=0; i<vertices; ++i)
            dist[i] = Integer.MAX_VALUE;

        // Add start node to priority queue.
        // Weight starts as 0
        pQueue.add(new Node(start, 0));
        dist[start] = 0;

        // Loop until all vertices are visited
        while (!pQueue.isEmpty()){
            // Remove the min distance node from priority queue
            int minVal = pQueue.poll().node;
            // Mark node as visited
            visited.add(minVal);
            // Now process all neighbor nodes from current node
            shortestPathHelper(minVal);
        }
        return dist;
    }

    private void shortestPathHelper(int current){
        //int edgeDist = -1;
        int newDist = -1;
        int len = adj[current].size();

        // Traverse all neighbors of current
        for(int i=0; i<len; ++i){
            Node node = adj[current].get(i);
            // If current node is not visited
            if(!visited.contains(node.node)){
                newDist = dist[current] + node.cost;
                // If new distance is lower in cost,
                // make it current distance
                if(newDist < dist[node.node]) {
                    dist[node.node] = newDist;
                    // Finally, add current node to priority queue
                    pQueue.add(new Node(node.node, dist[node.node]));
                }
            }
        }
    }
}
