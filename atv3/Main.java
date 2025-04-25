package atv3;

public class Main {
   public static void main(String[] args) {
      // Exemplo de uso
      Graph graph = new Graph(5);
      
      // Adicionando arestas com pesos
      graph.addEdge(1, 2, 5);
      graph.addEdge(1, 3, 3);
      graph.addEdge(2, 4, 2);
      graph.addEdge(3, 4, 6);
      graph.addEdge(2, 5, 7);
      graph.addEdge(4, 5, 1);
      
      // Encontrando caminho mínimo de 1 para 5
      Graph.PathResult result = graph.findShortestPath(1, 5);
      
      if (result != null) {
         System.out.println("Caminho mínimo encontrado:");
         System.out.println(result);
      } else {
         System.out.println("Não há caminho entre os vértices especificados.");
      }
   }
}