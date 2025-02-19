import java.util.Scanner;

public class parenteses {
  public static boolean teste(String s) {
    int cont = 0;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(') {
        cont++;
      } else if (c == ')') {
        cont--;
      }

      if (cont < 0) {
        return false;
      }
    }

    return cont == 0;
  }

  public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
          String entrada = scanner.nextLine();
          if (entrada.equals("FIM")) {
            return;
          }
            if (teste(entrada)) {
                System.out.println("correto");
            } else {
                System.out.println("incorreto");
            }
        }
        while (scanner.hasNext()); 
            
        
        scanner.close();
    }
}
