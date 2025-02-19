import java.util.Scanner;
import java.util.Stack;

public class q1068 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    while (sc.hasNextLine()) {
      String exp = sc.nextLine();
      Stack<Character> stack = new Stack<>();
      boolean incorrect = false;

      for (int i = 0; i < exp.length(); i++) {
        char ch = exp.charAt(i);

        if (ch == '(') {
          stack.push(ch);
        } else if (ch == ')') {
          if (stack.isEmpty()) {
            incorrect = true;
            break;
          } else {
            stack.pop();
          }
        }
      }

      if (!incorrect && stack.isEmpty()) {
        System.out.println("correct");
      } else {
        System.out.println("incorrect");
      }
    }
  }
}
