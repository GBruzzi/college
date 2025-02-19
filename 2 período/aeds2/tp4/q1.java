package tp4;

import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



class Pokemon {
    private int id;
    private int generation;
    private String name;
    private String description;
    private ArrayList<String> types;
    private ArrayList<String> abilities;
    private double weight;
    private double height;
    private int captureRate;
    private boolean isLegendary;
    private LocalDate captureDate;


    public Pokemon () { }

    public Pokemon (int id, int generation, String name, 
    String description, ArrayList<String> types, ArrayList<String> abilities, double weight, 
    double height, int captureRate, boolean isLegendary, LocalDate captureDate) {
        setId(id);
        setGeneration(generation);
        setName(name);
        setDescription(description);
        setTypes(types);
        setAbilities(abilities);
        setWeight(weight);
        setHeight(height);
        setCaptureRate(captureRate);
        setIsLegendary(isLegendary);
        setCaptureDate(captureDate);
    }

    // getters and setters
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

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public ArrayList<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<String> abilities) {
        this.abilities = abilities;
    }

    public void setAbilities(String abilities) {     
        abilities = abilities.replaceAll("[\\[\\]\"']", "").trim();       
        this.abilities = new ArrayList<>(Arrays.asList(abilities.split(",\\s*")));
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

    public boolean getIsLegendary() {
        return isLegendary;
    }

    public void setIsLegendary(boolean isLegendary) {
        this.isLegendary = isLegendary;
    }

    public LocalDate getCaptureDate() {
        return captureDate;
    }

    public void setCaptureDate(LocalDate captureDate) {
        this.captureDate = captureDate;
    }

    void read (String csvLine) {
        String[] data = csvLine.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
        
        setId(Integer.parseInt(data[0]));
        setGeneration(Integer.parseInt(data[1]));
        setName(data[2]);
        setDescription(data[3]);

        ArrayList<String> typesList = new ArrayList<>();
        typesList.add(data[4]);
        if (!data[5].isEmpty()) typesList.add(data[5]);
        setTypes(typesList);

        String abilitiesStr = data[6].replace("[", "").replace("]", "").replace("'", "").trim();
        setAbilities(abilitiesStr);

        if (!data[7].isEmpty()) {
            setWeight(Double.parseDouble(data[7]));
        } else {
            setWeight(0);
        }

        if (!data[8].isEmpty()) {
            setHeight(Double.parseDouble(data[8]));
        } else {
            setHeight(0); 
        }

        if (!data[9].isEmpty()) {
            setCaptureRate(Integer.parseInt(data[9]));
        } else {
            setCaptureRate(0); 
        }

        setIsLegendary(data[10].equals("1") || data[10].equalsIgnoreCase("true"));

        LocalDate date = parseDate(data[11]);
        setCaptureDate(date);

    }

    private LocalDate parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateStr, formatter);
    }

    String imprimir () {
        StringBuilder sb = new StringBuilder();
        sb.append("[#");
        sb.append(getId()).append(" -> ");
        sb.append(getName()).append(": ");
        sb.append(getDescription()).append(" - ['");

        if (getTypes().size() > 0) {
            sb.append(getTypes().get(0));
        }
        sb.append("'");
        if (getTypes().size() > 1) {
            sb.append(", '");
            sb.append(getTypes().get(1)).append("'");
        }
        sb.append("] - ");

        sb.append("[");
        for (int i = 0 ; i < getAbilities().size() ; i++) {
            sb.append("'");
            sb.append(getAbilities().get(i));
            sb.append("'");
            if (i < getAbilities().size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("] - ");
        sb.append(getWeight()).append("kg - ");
        sb.append(getHeight()).append("m - ");
        sb.append(getCaptureRate()).append("% - ");
        sb.append(getIsLegendary() ? "true" : "false").append(" - ");
        sb.append(getGeneration()).append(" gen] - ");
        sb.append(getCaptureDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        return sb.toString();
    }

    public static Pokemon findPokByName(ArrayList<Pokemon> pokedex, String name) {
        for (Pokemon p : pokedex) {
            if (p.getName().equals(name)) {
                return p;
            }
        }

        return null;
    }

}

class No {
    public Pokemon pokemon;
    public No esq;
    public No dir;

    public No (Pokemon pokemon) {
        this(pokemon, null, null);
    }

    public No (Pokemon pokemon, No esq, No dir) {
        this.pokemon = pokemon;
        this.esq = esq;
        this.dir = dir;
    }
}

class BinaryTree {
    private No raiz;

    public BinaryTree() {
        raiz = null;
    }

    public boolean pesquisar(String name) {
        return pesquisar(name, raiz);
    }

    private boolean pesquisar(String name, No i) {
        boolean resp;
        if (i == null) {
            resp = false;
        } else if (name.compareTo(i.pokemon.getName()) == 0) {
            resp = true;
        } else if (name.compareTo(i.pokemon.getName()) < 0) {
            System.out.print("esq ");
            resp = pesquisar(name, i.esq);
        } else {
            System.out.print("dir ");
            resp = pesquisar(name, i.dir);
        }

        return resp;
    }

    public void inserir(Pokemon p) {
        raiz = inserir(p, raiz);
    }

    private No inserir(Pokemon p, No i) {
        if (i == null) {
            i = new No(p);
        } else if (p.getName().compareTo(i.pokemon.getName()) < 0) {
            i.esq = inserir(p, i.esq);
        } else if (p.getName().compareTo(i.pokemon.getName()) > 0) {
            i.dir = inserir(p, i.dir);
        }

        return i;
    }

}


public class q1 {
    public static void main(String[] args) {
        String csvPath = "/tmp/pokemon.csv";
        ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();
        BinaryTree pokemonTree = new BinaryTree();

        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
            br.readLine(); 

            while (br.ready()) {
                String linha = br.readLine();
                Pokemon p = new Pokemon();
                p.read(linha);
                pokedex.add(p);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado " + csvPath);
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);

        String idStr = sc.nextLine();
        while (!idStr.equals("FIM")) {
            int id = Integer.parseInt(idStr);
            Pokemon p = pokedex.get(id - 1);
            if (p != null) {
                pokemonTree.inserir(p);
            }
            idStr = sc.nextLine();
        }

        String name = sc.nextLine();
        while (!name.equals("FIM")) {
            Pokemon p = Pokemon.findPokByName(pokedex, name);

            System.out.println(p.getName());
            System.out.print("=>raiz ");

            boolean found = pokemonTree.pesquisar(name);
            
            System.out.println(found ? "SIM" : "NAO");

            name = sc.nextLine();
        }

        sc.close();
    }
}