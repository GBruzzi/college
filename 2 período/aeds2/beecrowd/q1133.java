import java.util.Scanner;

public class q1133 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n1 = sc.nextInt();
    int n2 = sc.nextInt();

    for (int i = n1 + 1; i < n2; i ++) {
      if (i % 5 == 2 || i % 5 == 3) {
        System.err.println(i);
      }
    }
  }
}
