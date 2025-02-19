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

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getCaptureRate() {
        return captureRate;
    }

    public void setCaptureRate(int captureRate) {
        this.captureRate = captureRate;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    public void setLegendary(boolean legendary) {
        isLegendary = legendary;
    }

    public LocalDate getCaptureDate() {  
        return captureDate;
    }

    public void setCaptureDate(LocalDate captureDate) {  
        this.captureDate = captureDate;
    }


    // metodo clone
    public Pokemon clone() {
        return new Pokemon(
            this.id,
            this.generation,
            this.name,
            this.description,
            this.types,
            this.abilities,
            this.weight,
            this.height,
            this.captureRate,
            this.isLegendary,
            this.captureDate
        );
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

public class Q1 {
    static List<Pokemon> pokemons = new ArrayList<>();
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        preencherPok("pokemon.csv"); // Carrega os dados do CSV no array de Pokémons

        Scanner sc = new Scanner(System.in);
        String entrada;

        // Processa os IDs de Pokémon da entrada padrão até encontrar "FIM"
        while (true) {
            entrada = sc.nextLine();
            if (entrada.equals("FIM")) {
                break;
            }

            int id = Integer.parseInt(entrada);
            Pokemon pokemon = buscarPokemonPorId(id); // Busca o Pokémon pelo ID

            if (pokemon != null) {
                pokemon.imprimir(); // Imprime o Pokémon encontrado
            }
        }
        sc.close();
    }

    // Método para preencher a lista de Pokémons a partir de um arquivo CSV
    public static void preencherPok(String caminhoArquivo) {
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

                pokemons.add(pokemon);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar um Pokémon pelo ID
    public static Pokemon buscarPokemonPorId(int id) {
        for (Pokemon pokemon : pokemons) {
            if (pokemon.getId() == id) {
                return pokemon;
            }
        }
        return null; // Retorna null se o Pokémon com o ID não for encontrado
    }
}