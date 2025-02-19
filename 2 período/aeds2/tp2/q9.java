import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.time.*;
import java.util.*;

class Pokemon {
    private int id;
    private int generation;
    private String name;
    private String description;
    private List<String> types;
    private List<String> abilities;
    private double weight;
    private double height;
    private int captureRate;
    private boolean isLegendary;
    private LocalDate captureDate;  


    // Construtor padrão
    public Pokemon() {
        this.id = 0;
        this.generation = 1;
        this.name = "Unknown";
        this.description = "No description available.";
        this.types = null;
        this.abilities = null;
        this.weight = 0.0;
        this.height = 0.0;
        this.captureRate = 0;
        this.isLegendary = false;
        this.captureDate = null;  
    }

    // Construtor
    public Pokemon(int id, int generation, String name, String description, List<String> types,
                   List<String> abilities, double weight, double height, int captureRate,
                   boolean isLegendary, LocalDate captureDate) {  
        this.id = id;
        this.generation = generation;
        this.name = name;
        this.description = description;
        this.types = types;
        this.abilities = abilities;
        this.weight = weight;
        this.height = height;
        this.captureRate = captureRate;
        this.isLegendary = isLegendary;
        this.captureDate = captureDate;
    }

    // Getters e Setters
    public int getCaptureRate() {
        return captureRate;
    }

    public LocalDate getCaptureDate() {  
        return captureDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public String getName() {
        return name;
    }

    public double getHeight() {
      return height;
  }

    public void imprimir() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String capturaFormatada = (captureDate != null) ? captureDate.format(dtf) : "Data não disponível";
    
        // Formatar tipos corretamente
        String tiposFormatados = types.stream()
                                      .filter(type -> type != null)
                                      .map(type -> "'" + type.replace("\"", "") + "'")
                                      .reduce((type1, type2) -> type1 + ", " + type2)
                                      .orElse("['sem tipo']");
    
        String habilidadesFormatadas = abilities.stream()
                                                .map(ability -> ability.replace("[", "").replace("]", "").replace("\"", ""))
                                                .reduce((ability1, ability2) -> ability1 + ", " + ability2)
                                                .orElse("['sem habilidades']");
    
        System.out.println("[#" + id + " -> " + name + 
                           ": " + description + 
                           " - [" + tiposFormatados + "]" +  
                           " - [" + habilidadesFormatadas + "]" +  
                           " - " + weight + "kg" + 
                           " - " + height + "m" + 
                           " - " + captureRate + "%" + 
                           " - " + isLegendary + 
                           " - " + generation + " gen] - " + capturaFormatada);
    }
  }


  public class q9 {

    static List<Pokemon> pokemons = new ArrayList<>();
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static int numComparacoes = 0;
    static int numMovimentacoes = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String entrada;
        List<Integer> ids = new ArrayList<>();

        // Lê entradas do usuário até que "FIM" seja digitado
        while (true) {
            entrada = sc.nextLine();
            if (entrada.equals("FIM")) {
                break;
            }

            int id = Integer.parseInt(entrada);
            ids.add(id);
        }

        // Chama o método preencherPok passando o caminho do arquivo e a lista de IDs
        preencherPok("/tmp/pokemon.csv", ids);

        // Inicia a medição de tempo
        long startTime = System.nanoTime();
        
        HeapSortPokemon(pokemons);

        // Finaliza a medição de tempo
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime; // tempo de execução em nanossegundos

        for (int i = 0; i < pokemons.size(); i++) {
            pokemons.get(i).imprimir();
        }

        // Cria o arquivo de log
        criarArquivoDeLog(executionTime);

        sc.close(); // Fechar o scanner para evitar vazamentos de recursos
    }

    public static void preencherPok(String caminhoArquivo, List<Integer> ids) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
            reader.readLine(); // Ignora o cabeçalho

            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dadospok = linha.split("\"");
                String habilidades = dadospok[1];
                String[] primeiraParte = dadospok[0].split(",", -1);
                String[] segundaParte = dadospok[2].split(",", -1);

                int id = Integer.parseInt(primeiraParte[0]);

                // Adiciona o Pokémon à lista apenas se o ID estiver na lista fornecida
                if (ids.contains(id)) {
                    int generation = Integer.parseInt(primeiraParte[1]);
                    String name = primeiraParte[2];
                    String description = primeiraParte[3];

                    List<String> types = new ArrayList<>();
                    types.add(primeiraParte[4]);
                    types.add(primeiraParte[5].isEmpty() ? null : primeiraParte[5]);

                    List<String> abilities = new ArrayList<>();
                    abilities.add(habilidades);

                    double weight = segundaParte[1].isEmpty() ? 0 : Double.parseDouble(segundaParte[1]);
                    double height = segundaParte[2].isEmpty() ? 0 : Double.parseDouble(segundaParte[2]);
                    int captureRate = segundaParte[3].isEmpty() ? 0 : Integer.parseInt(segundaParte[3]);
                    boolean isLegendary = segundaParte[4].charAt(0) == '0' ? false : true;
                    LocalDate captureDate = LocalDate.parse(segundaParte[5], dtf);

                    Pokemon pokemon = new Pokemon(id, generation, name, description, types, abilities, weight,
                            height, captureRate, isLegendary, captureDate);

                    pokemons.add(pokemon); // Adiciona apenas o Pokémon com ID correspondente
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void HeapSortPokemon(List<Pokemon> pokemons) {
        int n = pokemons.size();

        // Constrói o heap (rearranja o array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(pokemons, n, i);
        }

        // Extrai os elementos do heap um por um
        for (int i = n - 1; i > 0; i--) {
            // Move a raiz (o maior elemento) para o final do array
            Pokemon temp = pokemons.get(0);
            pokemons.set(0, pokemons.get(i));
            pokemons.set(i, temp);
            numMovimentacoes++;

            // Chama o heapify na subárvore reduzida
            heapify(pokemons, i, 0);
        }
    }

    // Função para manter a propriedade do heap
    private static void heapify(List<Pokemon> pokemons, int n, int i) {
        int largest = i;  // Inicializa o maior como raiz
        int left = 2 * i + 1;  // Filho à esquerda
        int right = 2 * i + 2;  // Filho à direita

        // Compara o filho à esquerda com a raiz
        if (left < n && comparePokemon(pokemons.get(left), pokemons.get(largest)) > 0) {
            largest = left;
            numComparacoes++;
        }

        // Compara o filho à direita com o maior até agora
        if (right < n && comparePokemon(pokemons.get(right), pokemons.get(largest)) > 0) {
            largest = right;
            numComparacoes++;
        }

        // Se o maior não é a raiz
        if (largest != i) {
            // Troca a raiz com o maior
            Pokemon swap = pokemons.get(i);
            pokemons.set(i, pokemons.get(largest));
            pokemons.set(largest, swap);
            numMovimentacoes++;

            // Recursivamente, heapifica a subárvore afetada
            heapify(pokemons, n, largest);
        }
    }

    // Função para comparar Pokémon considerando altura e nome (em caso de empate)
    private static int comparePokemon(Pokemon p1, Pokemon p2) {
        // Primeiro, compara pelas alturas
        int heightComparison = Double.compare(p1.getHeight(), p2.getHeight());

        // Se as alturas forem iguais, compara pelo nome
        if (heightComparison == 0) {
            return p1.getName().compareTo(p2.getName());
        }

        // Caso contrário, retorna a comparação das alturas
        return heightComparison;
    }

    // Função para criar o arquivo de log
    private static void criarArquivoDeLog(long executionTime) {
        String matricula = "813584"; // Substitua pelo número da matrícula
        String nomeArquivo = matricula + "_heapsort.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write(matricula + "\t" + executionTime + "\t" + numComparacoes + "\t" + numMovimentacoes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
