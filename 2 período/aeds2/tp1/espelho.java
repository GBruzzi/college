import java.util.Scanner;


public class espelho {
  public static void imprimir(int n1, int n2) {
    int temp = n1;
    while (temp <= n2) {
      System.out.print(temp);
      temp++;
    }
    temp--;
    while (temp >= n1) {
      System.out.print(temp);
      temp--;
    }
  }


  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    do {
      int numero1 = scanner.nextInt();  
      int numero2 = scanner.nextInt(); 
      imprimir(numero1, numero2); 
    } while (scanner.hasNextLine());
  }

}
