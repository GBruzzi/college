import java.util.Scanner;
import java.util.ArrayList;

public class q1259 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    ArrayList<Integer> par = new ArrayList<>();
    ArrayList<Integer> impar = new ArrayList<>();
    
    for (int i = 0; i < t; i ++) {
      int n = sc.nextInt();
      if (n % 2 == 0) {
        par.add(n);
        continue;
      }
      impar.add(n);
    }

    ordenar(par);
    for (int n : par) {
      System.out.println(n);
    }

    ordenar(impar);
    for (int i = impar.size() - 1; i >= 0; i --) {
      System.out.println(impar.get(i));
    }

  }

  public static void ordenar(ArrayList<Integer> list) {
    for (int i = 0; i < list.size() - 1; i ++) {
      int min = i;
      for (int j = i + 1; j < list.size(); j ++) {
        if (list.get(j) < list.get(min)) {
          min = j;
        }
      }

      if (i != min) {
        int temp = list.get(i);
        list.set(i, list.get(min));  
        list.set(min, temp);         
    }
      
    }
  }
}
