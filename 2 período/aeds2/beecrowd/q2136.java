// https://judge.beecrowd.com/pt/problems/view/2136

import java.util.*;

class Pessoa {
  String nome;
  String escolha;

  Pessoa(String nome, String escolha) {
    this.nome = nome;
    this.escolha = escolha;
  }
}

public class q2136 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String teste = "";
    ArrayList<Pessoa> vai = new ArrayList<>();
    ArrayList<Pessoa> naoVai = new ArrayList<>();
    while (true) {
      teste = sc.nextLine();
      if (teste.equals("FIM")) {
        break;
      }

      String[] separado = teste.split(" ");

      if (separado[1].equals("NO")) {
        naoVai.add(new Pessoa(separado[0], separado[1]));
      } else {
        vai.add(new Pessoa(separado[0], separado[1]));
      }
    }

    for (int i = 0; i < vai.size(); i ++) {
      System.out.println(vai.get(i).nome);
    }
  }


  
}