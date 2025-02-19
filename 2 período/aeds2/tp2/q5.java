import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.time.Duration;
import java.time.Instant;

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


  public class q5 {

    static List<Pokemon> pokemons = new ArrayList<>();
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static int comparacoes = 0;  // Contador de comparações
    static int trocas = 0;       // Contador de trocas

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

        // Inicia o cronômetro para medir o tempo de execução do algoritmo de ordenação
        Instant start = Instant.now();
        
        SelectionSortPokemon(pokemons);

        // Finaliza o cronômetro
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);

        // Imprime os pokémons ordenados
        for (int i = 0; i < pokemons.size(); i++) {
            pokemons.get(i).imprimir();
        }

        // Escreve o log no arquivo matrícula_selectionsort.txt
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("813584_selecao.txt"));
            writer.write("813584" + "\t" + timeElapsed.toMillis() + "ms" + "\t" + comparacoes + "\t" + trocas);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public static Pokemon buscarPokemonPorId(int id) {
        for (Pokemon pokemon : pokemons) {
            if (pokemon.getId() == id) {
                return pokemon;
            }
        }
        return null; // Retorna null se o Pokémon com o ID não for encontrado
    }

    // Algoritmo Selection Sort com contadores de comparações e trocas
    public static void SelectionSortPokemon(List<Pokemon> pokemons) {
        for (int i = 0; i < pokemons.size() - 1; i++) {
            int menor = i;

            for (int j = i + 1; j < pokemons.size(); j++) {
                comparacoes++;  // Incrementa o contador de comparações
                if (pokemons.get(j).getName().compareTo(pokemons.get(menor).getName()) < 0) {
                    menor = j;
                }
            }

            // Realiza a troca se necessário
            if (i != menor) {
                Pokemon tmp = pokemons.get(i);
                pokemons.set(i, pokemons.get(menor));
                pokemons.set(menor, tmp);
                trocas++;  // Incrementa o contador de trocas
            }
        }
    }
}