import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
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

    public void imprimir() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String capturaFormatada = (captureDate != null) ? captureDate.format(dtf) : "Data não disponível";

        // Formatar tipos corretamente
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

public class q13 {
    static List<Pokemon> pokemons = new ArrayList<>();
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static int numComparacoes = 0; // Para contar o número de comparações

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

        // Chama o método mergeSort na lista de Pokemons
        mergeSort(pokemons);

        // Imprime os pokémons ordenados
        for (Pokemon pokemon : pokemons) {
            pokemon.imprimir();
        }

        // Escreve o log com o número de comparações
        escreverLog(sc);

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

    public static void mergeSort(List<Pokemon> pokemons) {
        if (pokemons.size() < 2) return; // Se a lista tiver menos de 2 elementos, não há necessidade de ordenar

        int mid = pokemons.size() / 2;
        List<Pokemon> left = new ArrayList<>(pokemons.subList(0, mid));
        List<Pokemon> right = new ArrayList<>(pokemons.subList(mid, pokemons.size()));

        mergeSort(left);
        mergeSort(right);

        merge(pokemons, left, right);
    }

    private static void merge(List<Pokemon> pokemons, List<Pokemon> left, List<Pokemon> right) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            // Comparar tipos primeiro, e em caso de empate, comparar nomes
            int comparison = comparePokemons(left.get(i), right.get(j));
            if (comparison <= 0) {
                pokemons.set(k++, left.get(i++));
            } else {
                pokemons.set(k++, right.get(j++));
            }
        }

        // Copiar os elementos restantes se houver
        while (i < left.size()) {
            pokemons.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            pokemons.set(k++, right.get(j++));
        }
    }

    private static int comparePokemons(Pokemon p1, Pokemon p2) {
        // Comparar pelo primeiro tipo
        String type1 = p1.getTypes().isEmpty() ? "" : p1.getTypes().get(0);
        String type2 = p2.getTypes().isEmpty() ? "" : p2.getTypes().get(0);

        numComparacoes++; // Contar comparação
        int typeComparison = type1.compareTo(type2);
        if (typeComparison != 0) {
            return typeComparison; // Se os tipos são diferentes, retornar a comparação
        }

        // Se os tipos são iguais, comparar pelos nomes
        numComparacoes++; // Contar comparação
        return p1.getName().compareTo(p2.getName());
    }

    private static void escreverLog(Scanner sc) {
        String matricula = "813584";
        long tempoExecucao = System.nanoTime(); 
        tempoExecucao = System.nanoTime() - tempoExecucao; // Calcule o tempo de execução

        String resultado = matricula + "\t" + (tempoExecucao / 1_000_000) + " ms\t" + numComparacoes;
        try {
            Files.write(Paths.get("813584_mergesort.txt"), Collections.singletonList(resultado), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}