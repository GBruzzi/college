import java.util.Scanner;

public class palindromo {
  public static boolean isP(String s) {
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) != s.charAt(s.length() - 1 - i))
        return false;
    }
    return true;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    do {
      String entrada = scanner.nextLine();
      if (isP(entrada)) {
        System.out.println("SIM");
      } else {
        System.out.println("NAO");
      }
    }
    while (scanner.hasNextLine()); 
    scanner.close();

  }
}
