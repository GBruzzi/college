// prova pratica aeds2

import java.util.*;

public class alfabetoAlienigena {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int k = sc.nextInt();
    int n = sc.nextInt();
    sc.nextLine();

    String alfabeto = sc.nextLine();
    String enviada = sc.nextLine();


    boolean teste = false;
    for (int i =0; i < enviada.length();i ++) {
      teste = false;
      for (int j = 0; j < alfabeto.length(); j ++) {
        if (enviada.charAt(i) == alfabeto.charAt(j)) {
          teste = true;
          break;
        }
      }

      if (teste == false)
        break;
    }

    if (teste) {
      System.out.println("S");
    } else {
      System.out.println("N");
    }
  }
}
