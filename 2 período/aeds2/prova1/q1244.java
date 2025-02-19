  import java.util.*;

  public class q1244 {
    public static void ordena(String vet[]) {
      for (int i = 1; i < vet.length; i ++) {
        String key = vet[i];
        int k = i - 1;

        while (k >= 0 && vet[k].length() < key.length()) {
          vet[k + 1] = vet[k];
          k--;
        }

        vet[k + 1] = key;
      }
    }


    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);

      int n = sc.nextInt();
      sc.nextLine();

      for (int i = 0; i < n; i ++) {
        String s = sc.nextLine();
        String vet[] = s.split(" ");
        ordena(vet);
        for (int j = 0; j < vet.length; j ++) {
          System.out.print(vet[j] + " ");
        }

        if (i != n - 1) {
          System.out.println();
        }        
      }
    }
  }
