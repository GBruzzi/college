import java.io.*;
import java.util.*;

public class N02 {

    static int t = 0; 
    static int[] TD, TT; 
    static Integer[] pai; 
    static List<Integer>[] adj; 
    static List<String> arestasDeArvore = new ArrayList<>();
    static List<String> arestasDivergentes = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o caminho do arquivo:");
        String arquivo = sc.nextLine();
        System.out.println("Digite o número do vértice de origem:");
        int verticeOrigem = sc.nextInt();

        lerGrafo(arquivo);
        buscaProfundidade(verticeOrigem);

        System.out.println("Arestas de árvore:");
        for (String aresta : arestasDeArvore) {
            System.out.println(aresta);
        }

        System.out.println("Arestas divergentes:");
        for (String aresta : arestasDivergentes) {
            System.out.println(aresta);
        }
    }

    public static void lerGrafo(String arquivo) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(arquivo));
        int n = sc.nextInt(); 
        int m = sc.nextInt(); 

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        while (sc.hasNextInt()) {
            int origem = sc.nextInt();
            int destino = sc.nextInt();
            adj[origem].add(destino);
        }

        sc.close();
    }

    public static void buscaProfundidade(int v) {
        TD = new int[adj.length];
        TT = new int[adj.length];
        pai = new Integer[adj.length];
        Arrays.fill(TD, 0); // marcar tudo com 0
        Arrays.fill(TT, 0);
        Arrays.fill(pai, -1);

        // execua o dfs para v
        dfs(v);

        for (int w : adj[v]) {
            if (TD[w] == 0) {
                arestasDivergentes.add("Aresta divergente: " + v + " -> " + w);
            }
        }
    }

    public static void dfs(int v) {
        t++;
        TD[v] = t; 
        for (int w : adj[v]) {
            if (TD[w] == 0) { // se w nao tiver sido visitado
                arestasDeArvore.add("Aresta de árvore: " + v + " -> " + w);
                pai[w] = v;
                dfs(w);
            } else if (TT[w] == 0 && w != pai[v]) { 
                System.out.println("Aresta de retorno: " + v + " -> " + w);
            }
        }
        t++;
        TT[v] = t; 
    }
}
