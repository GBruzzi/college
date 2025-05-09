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

  public Pokemon() {
  }

  public Pokemon(int id, int generation, String name,
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

  void preencherPok(String csvLine) {
    String[] data = csvLine.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");

    setId(Integer.parseInt(data[0]));
    setGeneration(Integer.parseInt(data[1]));
    setName(data[2]);
    setDescription(data[3]);

    ArrayList<String> typesList = new ArrayList<>();
    typesList.add(data[4]);
    if (!data[5].isEmpty())
      typesList.add(data[5]);
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

  String imprimirPok() {
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
    for (int i = 0; i < getAbilities().size(); i++) {
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

  public static Pokemon findPokemonByName(ArrayList<Pokemon> pokedex, String name) {
    for (Pokemon p : pokedex) {
      if (p.getName().equals(name)) {
        return p;
      }
    }
    return null;
  }

}

class Hash {
  Pokemon tabela[];
  int m;

  public Hash() {
    this(21);
  }

  public int calcularHash(Pokemon p) {
    int soma = 0;
    for (int i = 0; i < p.getName().length(); i++) {
      soma += (int) p.getName().charAt(i);
    }
    return soma % m;
  }

  public Hash(int m) {
    this.m = m;
    this.tabela = new Pokemon[this.m];
    for (int i = 0; i < m; i++) {
      tabela[i] = null;
    }
  }

  public int calcularReHash(Pokemon p) {
    int sum = 0;
    for (int i = 0; i < p.getName().length(); i++) {
      sum += (int) p.getName().charAt(i);
    }
    return (sum + 1) % m;
  }

  public boolean insert(Pokemon p) {
    boolean ans = false;
    if (p != null) {
      int pos = calcularHash(p);
      if (tabela[pos] == null) {
        tabela[pos] = p;
        ans = true;
      } else {
        pos = calcularReHash(p);
        if (tabela[pos] == null) {
          tabela[pos] = p;
          ans = true;
        }
      }
    }

    return ans;
  }

  public boolean pesquisar(Pokemon p) {
    boolean resp = false;
    int pos = calcularHash(p);
    if (tabela[pos] == p) {
      System.out.println("(Posicao: " + pos + ") SIM");
      resp = true;
    } else if (tabela[pos] != null) {
      pos = calcularReHash(p);
      if (tabela[pos] == p) {
        System.out.println("(Posicao: " + pos + ") SIM");
        resp = true;
      } else if (!resp) {
        System.out.println("NAO");
      }
    }

    return resp;
  }
}

public class q6 {
  public static void main(String[] args) {
        String csvPath = "/tmp/pokemon.csv";
        ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();
        Hash hashDiretaReHash = new Hash();

        try (BufferedReader line = new BufferedReader(new FileReader(csvPath))) {
          line.readLine(); 

            while (line.ready()) {
                String linha = line.readLine();
                Pokemon p = new Pokemon();
                p.preencherPok(linha);
                pokedex.add(p);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Erro: Arquivo não encontrado em " + csvPath);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);

        String idStr = sc.nextLine();
        while (!idStr.equals("FIM")) {
            int id = Integer.parseInt(idStr);
            Pokemon p = pokedex.get(id - 1);
            if (p != null) {
                hashDiretaReHash.insert(p);
            }
            idStr = sc.nextLine();
        }

        String name = sc.nextLine();
        while (!name.equals("FIM")) {
            Pokemon p = Pokemon.findPokemonByName(pokedex, name);
            System.out.print("=> " + p.getName() + ": ");
            boolean found = hashDiretaReHash.pesquisar(p);
            name = sc.nextLine();
        }

        sc.close();
    }
}