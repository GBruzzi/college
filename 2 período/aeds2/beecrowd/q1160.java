import java.util.Scanner;

public class q1160 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();

    for (int i = 0; i < t; i ++) {
      int pa = sc.nextInt();
      int pb = sc.nextInt();
      double g1 = sc.nextDouble();
      double g2 = sc.nextDouble();

      int ans = 0;

      while (pa < pb ) {
        if (ans > 100) {
          break;
        }
        pa += pa * (g1 / 100);
        pb += pb * (g2 / 100);
        ans++;
      }

      if (ans > 100) {
        System.out.println("Mais de 1 seculo.");
      } else {
        System.out.println(ans + " anos.");
      }
    }
  }
}
