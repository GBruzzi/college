import java.util.Scanner;

public class sorvete {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int t = 1;

    while (true) {
      int p = sc.nextInt();
      int s = sc.nextInt();

      if (p == 0 && s == 0)
        break;

      int[] u = new int[s];
      int[] v = new int[s];

      for (int i = 0; i < s ; i ++) {
        u[i] = sc.nextInt();
        v[i] = sc.nextInt();
      }

      System.out.println("Teste " + t);
      t++;

      int maior = -1;
      int menor = 1111111111;

      for (int i = 0; i < s ; i ++) {
        if (u[i] < menor) {
          menor = u[i];
        }

        if (v[i] > maior) {
          maior = v[i];
        }
      }

      System.out.println(menor + " " + maior);
    }
  }
}
