import java.util.Scanner;
import java.util.ArrayList;

public class q1244 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();

    for (int i = 0; i < t; i++) {
      ArrayList<String> arr = new ArrayList<>();
      
      while (sc.hasNext()) {
        String s = sc.next();
        arr.add(s);
    }

      arr.sort(null);

      for (int j = arr.size() - 1; j >= 0; j--) {
        System.out.println(arr.get(j));
      }
    }
  }
}
