import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    // Getters
    public int getGeneration() {
        return generation;
    }

    public String getName() {
        return name;
    }

    // Método para imprimir
    public void imprimir() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String capturaFormatada = (captureDate != null) ? captureDate.format(dtf) : "Data não disponível";

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

public class q18 {

    static List<Pokemon> pokemons = new ArrayList<>();
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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

        // Preenche a lista de Pokémons
        preencherPok("pokemon.csv", ids);

        // Ordena usando QuickSort
        quickSortPokemon(0, pokemons.size() - 1);

        // Exibe os 10 primeiros Pokémon
        for (int i = 0; i < Math.min(10, pokemons.size()); i++) {
            pokemons.get(i).imprimir();
        }

        sc.close();
    }

    // Método para preencher a lista de Pokémons
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

    // Método QuickSort
    public static void quickSortPokemon(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);

            quickSortPokemon(low, pi - 1);  // Recursão para a primeira parte
            quickSortPokemon(pi + 1, high); // Recursão para a segunda parte
        }
    }

    // Particiona o array de Pokémons
    private static int partition(int low, int high) {
        Pokemon pivot = pokemons.get(high); // Pivô
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (pokemons.get(j).getGeneration() < pivot.getGeneration() || 
               (pokemons.get(j).getGeneration() == pivot.getGeneration() &&
                pokemons.get(j).getName().compareTo(pivot.getName()) < 0)) {
                i++;

                // Troca pokemons[i] e pokemons[j]
                Pokemon temp = pokemons.get(i);
                pokemons.set(i, pokemons.get(j));
                pokemons.set(j, temp);
            }
        }

        // Troca pokemons[i+1] e pokemons[high] (ou o pivô)
        Pokemon temp = pokemons.get(i + 1);
        pokemons.set(i + 1, pokemons.get(high));
        pokemons.set(high, temp);

        return i + 1;
    }
}
