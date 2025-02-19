// https://judge.beecrowd.com/pt/problems/view/1382

import java.util.*;

public class q1382 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    for (int i = 0; i < n; i ++) {
      int tam = sc.nextInt();
      int[] vet = new int[tam];

      for (int j = 0; j < tam; j ++) {
        vet[j] = sc.nextInt();
      }

      System.out.println(selectionModified(vet));
    }
  }

  static int selectionModified(int[] vet) {
    int trocas = 0;
    for (int i = 0; i < vet.length - 1; i ++) {
      int menor = i;

      for (int j = i + 1; j < vet.length; j ++) {
        if (vet[j] < vet[menor]) {
          menor = j;
        }
      }

      if (menor != i) {
        trocas++;
        int tmp = vet[menor];
        vet[menor] = vet[i];
        vet[i] = tmp;
      }
    }

    return trocas;
  }
}
