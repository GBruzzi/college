import java.util.*;

public class q2929 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    sc.nextLine();
    int[] vet = new int[1000];
    int cont = 0;


    for (int i = 0; i < n; i ++) {
      String op = sc.nextLine();

      if (op.equals("POP")){
        if (cont == 0)
          continue;

        vet[cont - 1] = 0;
        n--;
      } else if (op.equals("MIN")) {
        int menor = 999999999;

        for (int j = 0; j < cont; j ++) {
          if (vet[j] < menor && vet[j] != 0) {
            menor = vet[j];
          }
        }

        System.out.println(menor);
      } else {
        String number = op.substring(5);
        int numero = Integer.parseInt(number);

        vet[cont] = numero;
        cont++;
      }
    }
  }
}
