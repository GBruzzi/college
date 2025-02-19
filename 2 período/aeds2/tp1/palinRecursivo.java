import java.util.Scanner;

public class palinRecursivo {
  
  public static boolean isP(String s) {
    if (s.length() <= 1) {
      return true;
    }
    
    if (s.charAt(0) != s.charAt(s.length() - 1)) {
      return false;
    }
    
    return isP(s.substring(1, s.length() - 1));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    do {
      String entrada = scanner.nextLine();
      if (entrada.equals("FIM")) 
                return;
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
