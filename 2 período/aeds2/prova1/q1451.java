import java.util.*;

public class q1451 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    while (sc.hasNextLine()) {
      String s = sc.nextLine();

      String ans = "";
      for (int i = 0; i < s.length(); i ++) {
        if (s.charAt(i) != '[' && s.charAt(i) != ']') {
          ans += s.charAt(i);
          continue;
        }

        if (s.charAt(i) == '[') {
          for (int j = i + 1; j< s.length(); j ++) {
            if (s.charAt(j) == ']') {
              i = j;
              break;
            }
          }
        }
      }

      System.out.println(ans);
    }
  }
}
