import java.util.Scanner;

public class q2482 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    sc.nextLine();
    String[] arr = new String[n * 2];

    for (int i = 0; i < arr.length; i++) {
      arr[i] = sc.nextLine();
    }

    int m = sc.nextInt();
    sc.nextLine();
    String[] criancas = new String[m * 2];

    for (int i = 0; i < criancas.length; i++) {
      criancas[i] = sc.nextLine();
    }

    for (int i = 1; i < criancas.length; i += 2) {
      for (int j = 0; j < arr.length; j += 2) {
        if (criancas[i].equals(arr[j])) {
          System.out.println(criancas[i - 1]);
          System.out.println(arr[j + 1]);
          System.out.println("\n");

          break;
        }
      }
    }

    sc.close();
  }
}
