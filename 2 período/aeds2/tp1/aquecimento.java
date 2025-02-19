import java.util.Scanner;

public class aquecimento {
  public static int aquecimento(String s) {
    int ans = 0;

    for (int i = 0; i < s.length(); i ++) {
      if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
        ans++;
    }

    return ans;
  }


  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    do {
      String entrada = scanner.nextLine();
      if (entrada.equals("FIM"))
        break;
      System.out.println(aquecimento(entrada));
    } while (scanner.hasNextLine());
    scanner.close();
  }
}
