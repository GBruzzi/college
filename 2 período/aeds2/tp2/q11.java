import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        this.types = new ArrayList<>();
        this.abilities = new ArrayList<>();
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

    public List<String> getTypes() {
        return types;
    }

    public String getName() {
        return name;
    }

    public int getCaptureRate() {
        return captureRate;
    }

    public void imprimir() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String capturaFormatada = (captureDate != null) ? captureDate.format(dtf) : "Data não disponível";

        String tiposFormatados = String.join(", ", types);
        String habilidadesFormatadas = String.join(", ", abilities);

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

public class q11 {
    static List<Pokemon> pokemons = new ArrayList<>();
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static int numComparisons = 0; // Contador de comparações
    static int numMovements = 0; // Contador de movimentações
    static String matricula = "2024123456"; // Substituir pela sua matrícula

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

        preencherPok("/tmp/pokemon.csv", ids);

        // Inicializa o contador de tempo
        long startTime = System.currentTimeMillis();

        // Executa o Counting Sort com desempate por nome
        countingSortPokemon(pokemons);

        // Calcula o tempo de execução
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        // Imprime os pokémons ordenados
        for (Pokemon pokemon : pokemons) {
            pokemon.imprimir();
        }

        // Escreve no arquivo de log
        writeLog(executionTime);

        sc.close(); // Fechar o scanner para evitar vazamentos de recursos
    }

    public static void preencherPok(String caminhoArquivo, List<Integer> ids) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            reader.readLine(); // Ignora o cabeçalho

            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dadospok = linha.split("\"");
                String habilidades = dadospok[1];
                String[] primeiraParte = dadospok[0].split(",", -1);
                String[] segundaParte = dadospok[2].split(",", -1);

                int id = Integer.parseInt(primeiraParte[0]);

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
                    boolean isLegendary = segundaParte[4].charAt(0) != '0';
                    LocalDate captureDate = LocalDate.parse(segundaParte[5], dtf);

                    Pokemon pokemon = new Pokemon(id, generation, name, description, types, abilities, weight,
                            height, captureRate, isLegendary, captureDate);

                    pokemons.add(pokemon); // Adiciona apenas o Pokémon com ID correspondente
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void countingSortPokemon(List<Pokemon> pokemons) {
        int maxCaptureRate = findMaxCaptureRate(pokemons);

        // Cria o array de contagem
        int[] count = new int[maxCaptureRate + 1];

        // Conta a ocorrência de cada captureRate
        for (Pokemon pokemon : pokemons) {
            count[pokemon.getCaptureRate()]++;
        }

        // Modifica o array count para armazenar as posições de saída
        for (int i = 1; i <= maxCaptureRate; i++) {
            count[i] += count[i - 1];
        }

        // Array de saída que será ordenado
        Pokemon[] output = new Pokemon[pokemons.size()];

        // Constrói o array de saída em ordem inversa para garantir a estabilidade
        for (int i = pokemons.size() - 1; i >= 0; i--) {
            Pokemon pokemon = pokemons.get(i);
            int index = count[pokemon.getCaptureRate()] - 1;

            output[index] = pokemon;
            count[pokemon.getCaptureRate()]--;

            numMovements++; // Conta uma movimentação
        }

        // Ordena por nome para resolver o empate, mantendo os captureRates iguais juntos
        Arrays.sort(output, Comparator.comparing(Pokemon::getCaptureRate).thenComparing(Pokemon::getName));

        // Atualiza a lista original com o array ordenado
        for (int i = 0; i < output.length; i++) {
            pokemons.set(i, output[i]);
        }
    }

    private static int findMaxCaptureRate(List<Pokemon> pokemons) {
        int max = Integer.MIN_VALUE;
        for (Pokemon pokemon : pokemons) {
            numComparisons++; // Conta uma comparação
            if (pokemon.getCaptureRate() > max) {
                max = pokemon.getCaptureRate();
            }
        }
        return max;
    }

    // Função para escrever no arquivo de log
    private static void writeLog(long executionTime) {
        String fileName = matricula + "813584_coutingsort.txt"; 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(matricula + "\t" + executionTime + "\t" + numComparisons + "\t" + numMovements);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}