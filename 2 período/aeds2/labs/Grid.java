import java.util.*;

public class Grid {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    while (sc.hasNextInt()) {
      int n = sc.nextInt();

      int[] largada = new int[n];
      int[] chegada = new int[n];

      for (int i = 0; i < n; i++) {
        largada[i] = sc.nextInt();
      }

      for (int i = 0; i < n; i++) {
        chegada[i] = sc.nextInt();
      }

      int res = 0;

      for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
          if (largada[i] == chegada[j]) {
            res += j - i;
          }
        }
      }

      System.out.println(res);

    }

    sc.close();
  }
}
