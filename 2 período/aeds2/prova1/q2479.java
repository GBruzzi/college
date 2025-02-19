import java.util.*;

public class q2479 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    sc.nextLine();

    ArrayList<String> criancas = new ArrayList<>();
    int comp = 0;
    int ncomp = 0;
    for (int i = 0; i < n; i ++) {
      String crianca = sc.nextLine();

      if (crianca.charAt(0) == '+') {
        comp++;
      } else {
        ncomp ++;
      }

      criancas.add(crianca.substring(2));
    }

    ordena(criancas);

    for (int i = 0; i < criancas.size(); i ++) {
      System.out.println(criancas.get(i));
    }
    System.out.println("Se comportam: " + comp + " | Nao se comportaram: " + ncomp);
    
  }

  public static void ordena(ArrayList<String> str) {
    for (int i = 1; i <str.size(); i ++) {
      String key = str.get(i);
      int j = i - 1;

      while (j >= 0 && str.get(j).compareTo(key) > 0) {
        str.set(j + 1, str.get(j));
        j--;
      }

      str.set(j + 1, key);
    }
  }
}
