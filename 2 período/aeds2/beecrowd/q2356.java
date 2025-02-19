// https://judge.beecrowd.com/pt/problems/view/2356

import java.util.*;

public class q2356 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNextLine()) {
      String s1 = sc.nextLine();
      String s2 = sc.nextLine();

      if (s2.length() > s1.length()) {
        System.out.println("Nao resistente");
        return;
      }

      boolean res = false;
      for (int i = 0; i < s1.length(); i++) {
        if (s1.charAt(i) == s2.charAt(0)) {
          boolean teste = true;
          for (int j = i + 1; j < s2.length(); j++) {
            if (s1.charAt(j) != s2.charAt(j - i)) {
              teste = false;
              break;
            }
          }
          if (teste) {
            res = true;
          }
        }
      }

      if (!res) {
        System.out.println("Nao resistente");
      } else {
        System.out.println("Resistente");
      }
    }

  }
}