package grafos;

import java.io.*;
import java.util.*;

class graph {
  private int numVertices;
  private int numArestas;
  private Map<Integer, List<Integer>> adjacencias; // Sucessores (lista de adjacência)
  private Map<Integer, List<Integer>> predecessores; // Predecessores (lista de adjacência)

  public graph(int numVertices) {
    this.numVertices = numVertices;
    this.numArestas = 0;
    adjacencias = new HashMap<>();
    predecessores = new HashMap<>();

    // inicializar a lista
    for (int i = 1; i <= numVertices; i++) {
      adjacencias.put(i, new ArrayList<>());
      predecessores.put(i, new ArrayList<>());
    }
  }

  public void adicionarAresta(int origem, int destino) {
    adjacencias.get(origem).add(destino);
    predecessores.get(destino).add(origem);
    numArestas++;
  }

  public int grauSaida(int vertice) {
    return adjacencias.get(vertice).size();
  }

  public int grauEntrada(int vertice) {
    return predecessores.get(vertice).size();
  }

  public List<Integer> sucessores(int vertice) {
    return adjacencias.get(vertice);
  }

  public List<Integer> predecessores(int vertice) {
    return predecessores.get(vertice);
  }

}

public class N01 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Solicita o nome do arquivo ao usuário
    System.out.print("Digite o caminho do arquivo: ");
    String nomeArquivo = scanner.nextLine();

    File arquivo = new File(nomeArquivo);
    
    try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
      String linha = br.readLine(); // Lê a primeira linha
      if (linha != null) {
        String[] partes = linha.split(" ");
        int numVertices = Integer.parseInt(partes[0]);
        int numArestas = Integer.parseInt(partes[1]);

        graph grafo = new graph(numVertices);

        for (int i = 0; i < numArestas; i++) {
            linha = br.readLine();
            partes = linha.split(" ");
            int origem = Integer.parseInt(partes[0]);
            int destino = Integer.parseInt(partes[1]);
            grafo.adicionarAresta(origem, destino);
        }

        System.out.print("Digite o número do vértice: ");
        int vertice = scanner.nextInt();

        while (vertice < 1 || vertice > numVertices) {
          System.out.println("Erro: O número do vértice deve estar entre 1 e " + numVertices + ".");
          System.out.print("Digite o número do vértice novamente: ");
          vertice = scanner.nextInt();
      }

        System.out.println("Grau de saída do vértice " + vertice + ": " + grafo.grauSaida(vertice));
        System.out.println("Grau de entrada do vértice " + vertice + ": " + grafo.grauEntrada(vertice));

        System.out.print("Sucessores do vértice " + vertice + ": ");
        List<Integer> sucessores = grafo.sucessores(vertice);
        System.out.println(sucessores.isEmpty() ? "Nenhum sucessor" : sucessores);

        System.out.print("Predecessores do vértice " + vertice + ": ");
        List<Integer> predecessores = grafo.predecessores(vertice);
        System.out.println(predecessores.isEmpty() ? "Nenhum predecessor" : predecessores);
      }
    } catch (FileNotFoundException e) {
      System.out.println("Erro: Arquivo não encontrado!");
    } catch (IOException e) {
      System.out.println("Erro ao ler o arquivo!");
    }

    scanner.close();
  }
}