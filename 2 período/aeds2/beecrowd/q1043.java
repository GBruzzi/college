import java.util.Scanner;

public class q1043 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    double n1 = sc.nextDouble();
    double n2 = sc.nextDouble();
    double n3 = sc.nextDouble();

    if ((n1 + n2 ) > n3 && (n1 + n3) > n2 && n2 + n3 > n1) {
      double p = n1 + n2 + n3;      
      System.out.printf("Perimetro = %.1f\n", p);
    } else {
      double area = ((n1 + n2) * n3) / 2;
      System.out.printf("Area = %.1f\n", area);
    }
  }
}
