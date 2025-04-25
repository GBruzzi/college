package atv3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

class Graph {
   private final int vertices;
   private final List<List<Edge>> adjList;
   private final List<Edge> edges;
   private final boolean[] visited;
   private final List<Edge> treeEdges;

   public Graph(int var1) {
      this.vertices = var1;
      this.adjList = new ArrayList<>();
      this.edges = new ArrayList<>();
      this.visited = new boolean[var1 + 1];
      this.treeEdges = new ArrayList<>();

      for(int var2 = 0; var2 <= var1; ++var2) {
         this.adjList.add(new ArrayList<>());
      }
   }

   public void addEdge(int var1, int var2) {
      this.addEdge(var1, var2, 1.0);
   }

   public void addEdge(int var1, int var2, double weight) {
      Edge edge = new Edge(var1, var2, weight);
      this.adjList.get(var1).add(edge);
      this.edges.add(edge);
   }

   public void sortEdges() {
      Iterator var1 = this.adjList.iterator();

      while(var1.hasNext()) {
         List var2 = (List)var1.next();
         Collections.sort(var2, (e1, e2) -> Integer.compare(e1.v, e2.v));
      }
   }

   public void dfs(int var1) {
      this.visited[var1] = true;
      Iterator var2 = this.adjList.get(var1).iterator();

      while(var2.hasNext()) {
         Edge edge = (Edge)var2.next();
         if (!this.visited[edge.v]) {
            this.treeEdges.add(edge);
            this.dfs(edge.v);
         }
      }
   }

   public void classifyEdges(int var1) {
      Iterator var2 = this.edges.iterator();

      while(var2.hasNext()) {
         Edge var3 = (Edge)var2.next();
         if (var3.u == var1) {
            if (this.treeEdges.contains(var3)) {
               System.out.println("Aresta de Árvore: " + var3.u + " -> " + var3.v);
            } else {
               System.out.println("Aresta Divergente: " + var3.u + " -> " + var3.v);
            }
         }
      }
   }

   public PathResult findShortestPath(int source, int target) {
      double[] distances = new double[vertices + 1];
      int[] edgesCount = new int[vertices + 1];
      int[] previous = new int[vertices + 1];
      
      for (int i = 0; i <= vertices; i++) {
         distances[i] = Double.POSITIVE_INFINITY;
         edgesCount[i] = Integer.MAX_VALUE;
         previous[i] = -1;
      }
      
      distances[source] = 0;
      edgesCount[source] = 0;
      
      PriorityQueue<VertexInfo> pq = new PriorityQueue<>();
      pq.add(new VertexInfo(source, 0, 0));
      
      while (!pq.isEmpty()) {
         VertexInfo current = pq.poll();
         int u = current.vertex;
         
         if (current.distance > distances[u] || 
             (current.distance == distances[u] && current.edges > edgesCount[u])) {
            continue;
         }
         
         for (Edge edge : adjList.get(u)) {
            int v = edge.v;
            double newDistance = distances[u] + edge.weight;
            int newEdges = edgesCount[u] + 1;
            
            if (newDistance < distances[v] || 
                (newDistance == distances[v] && newEdges < edgesCount[v])) {
               distances[v] = newDistance;
               edgesCount[v] = newEdges;
               previous[v] = u;
               pq.add(new VertexInfo(v, newDistance, newEdges));
            }
         }
      }
      
      if (distances[target] == Double.POSITIVE_INFINITY) {
         return null;
      }
      
      List<Integer> path = new ArrayList<>();
      for (int v = target; v != -1; v = previous[v]) {
         path.add(v);
      }
      Collections.reverse(path);
      
      return new PathResult(distances[target], edgesCount[target], path);
   }
   
   private static class VertexInfo implements Comparable<VertexInfo> {
      int vertex;
      double distance;
      int edges;
      
      public VertexInfo(int vertex, double distance, int edges) {
         this.vertex = vertex;
         this.distance = distance;
         this.edges = edges;
      }
      
      @Override
      public int compareTo(VertexInfo other) {
         int cmp = Double.compare(this.distance, other.distance);
         if (cmp != 0) return cmp;
         return Integer.compare(this.edges, other.edges);
      }
   }
   
   public static class PathResult {
      public final double totalDistance;
      public final int totalEdges;
      public final List<Integer> path;
      
      public PathResult(double totalDistance, int totalEdges, List<Integer> path) {
         this.totalDistance = totalDistance;
         this.totalEdges = totalEdges;
         this.path = path;
      }
      
      @Override
      public String toString() {
         return "Distância total: " + totalDistance + 
                "\nNúmero de arestas: " + totalEdges + 
                "\nCaminho: " + path;
      }
   }
}