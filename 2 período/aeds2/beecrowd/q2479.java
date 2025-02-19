import java.util.*;

public class q2479 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt(); 
    sc.nextLine();
    String[] alunos = new String[n];
    int comp = 0;
    int ncomp = 0;

    for (int i = 0; i < n; i++) {
      alunos[i] = sc.nextLine();

      if (alunos[i].charAt(0) == '+') {
        comp++;
        alunos[i] = alunos[i].substring(2).trim();
      } else {
        ncomp++;
        alunos[i] = alunos[i].substring(2).trim();
      }

      
    }

    Arrays.sort(alunos);

    for (int i = 0; i < alunos.length; i ++) {
      System.out.println(alunos[i]);
    }


    System.out.println("Se comportaram: " + comp + " | Não se comportaram: " + ncomp);
    
  }


}
