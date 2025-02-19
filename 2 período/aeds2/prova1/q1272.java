import java.util.*;

public class q1272 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    sc.nextLine();

    for (int i= 0; i < n; i ++) {
      String s = sc.nextLine();

      String ans = new String();

      if (s.charAt(0) != '·' ) {
        ans += s.charAt(0);
      }

      for (int j = 0;j < s.length(); j ++) {
        if (s.charAt(j) == '·') {
          for (int o = j + 1; o < s.length(); o ++) {
            if (s.charAt(o) != '·') {
              ans += s.charAt(o);
              j += o - j;
              break;
            }            
          }
          
        }
      }

      System.out.println(ans);
    }
  }
}