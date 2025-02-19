import java.util.*;

public class q1259 {
  public static void selSort (ArrayList<Integer> arr) {
    for (int i = 0; i < arr.size() - 1; i ++) {
      int menor = i;

      for (int j = i + 1; j< arr.size(); j ++) {
        if (arr.get(j) < arr.get(menor)) {
          menor = j;
        }
      }

      int temp = arr.get(i);
      arr.set(i, arr.get(menor));  
      arr.set(menor, temp); 
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();

    ArrayList<Integer> pares = new ArrayList<>();
    ArrayList<Integer> impares = new ArrayList<>();

    for (int i = 0; i < n; i ++) {
      int number = sc.nextInt();

      if (number % 2 == 0) {
        pares.add(number);
      } else {
        impares.add(number);
      }
    }

    selSort(pares);
    selSort(impares);
    ArrayList<Integer> res = new ArrayList<>();

    for (int i = 0; i < pares.size(); i ++) {
      res.add(pares.get(i));
    }
    

    for (int i = impares.size() - 1; i >=0; i --) {
      res.add(impares.get(i));
    }

    for (int i = 0; i < n; i ++) {
      System.out.println(res.get(i));
    }
  }
}
