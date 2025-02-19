import java.util.*;

public class q1234 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    while (sc.hasNextLine()) {
      String str = sc.nextLine();
      int cont = 0;
      if (str.charAt(cont) == ' ') {
        while (str.charAt(cont) != ' ') {
          cont++;
        }
      }
      
      char mais = Character.toUpperCase(str.charAt(cont));

      String ans = "";
      ans += mais;

      int d = 0;
      for (int i = cont + 1; i < str.length(); i ++) {
        if (str.charAt(i) == ' ') {
          ans += ' ';
          continue;
        }
    
        if (d% 2 == 0) {
          ans += Character.toLowerCase(str.charAt(i));
          d++;
        } else {
          ans += Character.toUpperCase(str.charAt(i));
          d++;
        }
        
      }

      System.out.println(ans);
    }
  }
}
