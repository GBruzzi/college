import java.util.*;

public class q2174 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    sc.nextLine();
    int cont = 0;

    String[] pokemons = new String[n];
    for (int i =0; i < n; i ++) {
      boolean present = false;
      String pok = sc.nextLine();
      for (int j = 0; j < cont; j ++) {

        if (pokemons[j].equals(pok)) {
          present = true;
          break;
        }
      }

      if (!present) {
        pokemons[cont] = pok;
        cont++;
      }
    }

    int res = 151 - cont;
    System.out.println("Falta(m) " + res + " pomekon(s).");
    sc.close();
  }
}
