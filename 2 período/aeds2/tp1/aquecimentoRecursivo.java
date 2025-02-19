import java.util.Scanner;

public class aquecimentoRecursivo {
  public static int contarMaiusculas(String s, int index) {
    if (index == s.length()) {
      return 0;
    }

    int count = (s.charAt(index) >= 'A' && s.charAt(index) <= 'Z') ? 1 : 0;

    return count + contarMaiusculas(s, index + 1);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    do {
      String entrada = scanner.nextLine();
      if (entrada.equals("FIM"))
        break;
      System.out.println(contarMaiusculas(entrada, 0));
    } while (scanner.hasNextLine());
    scanner.close();
  }
}
