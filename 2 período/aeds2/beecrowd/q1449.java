import java.util.*;

public class q1449 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();

    for (int j = 0; j < t; j++) {
      int m = sc.nextInt();
      int n = sc.nextInt();
      sc.nextLine();

      String[] palavras = new String[m * 2];

      for (int i = 0; i < m * 2; i++) {
        palavras[i] = sc.nextLine();
      }

      String[] musica = new String[n];

      for (int i = 0; i < n; i++) {
        musica[i] = sc.nextLine();

      }

      

      for (int i =0;i < n;i ++) {
        String[] splitted = musica[i].split(" ");
        String ans = "";
        for (int o = 0; o < splitted.length; o ++){
          boolean foi = false;
          for (int k = 0; k < palavras.length; k += 2) {
            if (splitted[o].equals(palavras[k])) {
              ans += palavras[k + 1] + " ";
              foi = true;
              break;
            }
          }

          if (foi == false) {
            ans += splitted[o] + " ";
          }
        }

        System.out.println(ans);
      }      

      System.out.println();
    }

    sc.close();
  }
}