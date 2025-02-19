import java.util.*;

public class q3358 {
  public static boolean isV(char c) {
    char[] vogais = {'a','e','i','o','u','A','E','I','O','U'};

    for (int i = 0; i < 10; i ++) {
      if (vogais[i] == c) {
        return true;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    sc.nextLine();

    for (int i = 0; i < n; i++) {
      String input = sc.nextLine();
      boolean hard = false;

      for (int j = 0; j < input.length() - 2; j++) {
        char ch = input.charAt(j);

        if (!isV(ch)) {
          if (!isV(input.charAt(j + 1))) {
            if (!isV(input.charAt(j + 2))) {
              hard = true;
              break;
            }
          }
        }
       
      }

      if (hard) {
        System.out.println(input + " nao eh facil");
      } else {
        System.out.println(input + " eh facil");
      }
    }
  }
}
