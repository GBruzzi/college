import java.util.*;

public class q2929 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int op = sc.nextInt();
    sc.nextLine();
    int[] arr = new int[op];
    int n = 0;
    int menor = 99999999;
    for (int i = 0; i < op;i ++){
      String consulta = sc.nextLine();

      if (consulta.equals("POP")) {  
        if (n == 0)
          continue;
        arr[n - 1] = 0;
        n--;
      } else if (consulta.equals("MIN") ){        
        System.out.println(menor);
      } else {
        int numero = Integer.parseInt(consulta.substring(5));
        arr[n] = numero;
        if (numero < menor)
          menor = numero;
        n++;
      }
    }
  }


  
}