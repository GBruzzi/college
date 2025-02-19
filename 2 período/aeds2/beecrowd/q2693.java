// https://judge.beecrowd.com/pt/problems/view/2693

import java.util.*;

class Pessoa {
  String nome;
  char regiao;
  int distancia;

  Pessoa(String nome, char regiao, int distancia) {
    this.nome = nome;
    this.regiao = regiao;
    this.distancia = distancia;
  }
}

public class q2693 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    sc.nextLine();
    Pessoa[] arr = new Pessoa[n];

    for (int i = 0; i < n; i++) {
      String tot = sc.nextLine();
      String[] separados = tot.split(" ");
      int value = Integer.parseInt(separados[2]);
      char c = separados[1].charAt(0);
      arr[i] = new Pessoa(separados[0], c, value);          
    }

    ordenaVet(arr);

    for (int i = 0; i < n; i++) {
      System.out.println(arr[i].nome);
    }
    sc.close();
  }

  public static void ordenaVet(Pessoa vet[]) {
    for (int i = 1; i < vet.length; i++) {
      Pessoa key = vet[i];
      int actual = i;
      while (actual > 0 && (vet[actual - 1].distancia > key.distancia || 
      (vet[actual - 1].distancia == key.distancia && vet[actual - 1].regiao > key.regiao) || (vet[actual - 1].distancia == key.distancia && vet[actual - 1].regiao == key.regiao && vet[actual - 1].nome.compareTo(key.nome) > 0)
      )) {
        vet[actual] = vet[actual - 1];
        actual--;
      }
      vet[actual] = key;
    }
  }
}
