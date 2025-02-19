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
                " - [" + tiposFormatados +
                "] - [" + habilidadesFormatadas +
                "] - " + weight + "kg" +
                " - " + height + "m" +
                " - " + captureRate + "%" +
                " - " + isLegendary +
                " - " + generation + " gen] - " + capturaFormatada);
    }
}

public class q15 {

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

        // Chama o método preencherPok passando o caminho do arquivo e a lista de IDs
        preencherPok("/tmp/pokemon.csv", ids);

        // Ordenação parcial com k = 10 por nome
        SelectionSortPokemon(pokemons, 10);

        // Exibe os primeiros 10 pokémons ordenados
        int cont = 0;
        for (Pokemon pokemon : pokemons) {
            if (cont == 10)
                break;
            pokemon.imprimir();
            cont++;
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

    public static void SelectionSortPokemon(List<Pokemon> pokemons, int k) {
        k = Math.min(k, pokemons.size()); // Garante que k não seja maior que o tamanho da lista

        for (int i = 0; i < k - 1; i++) {
            int menor = i;

            for (int j = i + 1; j < pokemons.size(); j++) {
                Pokemon pokemonMenor = pokemons.get(menor);
                Pokemon pokemonAtual = pokemons.get(j);

                // Compara os nomes, e se forem iguais, desempata pelo ID
                if (pokemonAtual.getName().compareTo(pokemonMenor.getName()) < 0 ||
                    (pokemonAtual.getName().equals(pokemonMenor.getName()) && pokemonAtual.getId() < pokemonMenor.getId())) {
                    menor = j;
                }
            }

            // Troca os Pokémons de lugar
            Pokemon tmp = pokemons.get(i);
            pokemons.set(i, pokemons.get(menor));
            pokemons.set(menor, tmp);
        }
    }
}
