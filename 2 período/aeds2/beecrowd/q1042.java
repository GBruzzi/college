import java.util.Scanner;


public class q1042 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int maior, menor, meio;
    int n1 = scanner.nextInt();
    maior = n1; menor = n1; meio = n1;

    int n2 = scanner.nextInt();
    
    if (n2 > maior) {
      maior = n2;
    } else {
      menor = n2;
    }

    int n3 = scanner.nextInt();
    if (n3 > maior) {
      meio = maior;
      maior = n3;
    } else if (n3 < menor) {
      meio = menor;
      menor = n3;
    } else {
      meio = n3;
    }

    System.out.println(menor);
    System.out.println(meio);
    System.out.println(maior);
    System.out.println();
    System.out.println(n1);
    System.out.println(n2);
    System.out.println(n3);
  }  

}