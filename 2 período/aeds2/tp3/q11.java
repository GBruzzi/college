import java.util.Scanner;

class Node {
  int value;
  Node sup;
  Node dir;
  Node inf;
  Node esq;

  public Node() {
    this.sup = this. inf = this.dir = this.esq = null;
  }

  public Node(int value) {
    this();
    this.value = value;
  }
}

class Matriz {
  Node head;
  int totLn;
  int totCol;

  public Matriz(int ln, int col, Scanner sc) {
    this.totLn = ln;
    this.totCol = col;

    // cria e popula a matriz
    Node start = new Node();
    Node aux = new Node();

    for (int i = 0; i < ln; i++) {

      for (int j = 0; j < col; j++) {
        Node newNode = new Node(sc.nextInt());

        if (i == 0 && j == 0) {
          this.head = newNode;
          start = newNode;
          aux = newNode;
        } else if (i == 0) {
          newNode.esq = aux;
          aux.dir = newNode;
          aux = aux.dir;
        } else if (j == 0) {
          newNode.sup = start;
          start.inf = newNode;
          aux = newNode;
        } else {
          newNode.esq = aux;
          aux.dir = newNode;
          newNode.esq.sup.dir.inf = newNode;
          newNode.sup = newNode.esq.sup.dir;
          aux = aux.dir;
        }
      }

      if (i != 0)
        start = start.inf;

      aux = start;
    }
  }

  public void mostrarDiagonalPrincipal() {
    if (this.totLn != this.totCol)
      return;

    Node curr = head;
    while (curr != null) {
      System.out.print(curr.value + " ");

      if (curr.inf == null) 
        break;

      curr = curr.inf.dir;
    }

    System.out.println();
  }

  public void mostrarDiagonalSecundaria() {
    if (this.totLn != this.totCol)
      return;

    Node curr = head;

    while (curr.dir != null) {
      curr = curr.dir;
    }

    while (curr != null) {
      System.out.print(curr.value + " ");

      if (curr.esq == null)
        break;

      curr = curr.esq.inf;
    }

    System.out.println();
  }

  public static void soma(Matriz m1, Matriz m2) {
    Node aux1 = m1.head;
    Node aux2 = m2.head;

    while (aux1 != null && aux2 != null) {
      Node curr1 = aux1;
      Node curr2 = aux2;

      while (curr1 != null && curr2 != null) {
        int sum = curr1.value + curr2.value;
        System.out.print(sum + " ");

        curr1 = curr1.dir;
        curr2 = curr2.dir;
      }

      System.out.println();

      aux1 = aux1.inf;
      aux2 = aux2.inf;
    }
  }

  public static void multiplicar(Matriz m1, Matriz m2) {
    Node aux1 = m1.head;

    while (aux1 != null) {
      Node aux2 = m2.head;

      while (aux2 != null) {
        int sum = 0;

        Node curr1 = aux1;
        Node curr2 = aux2;

        while (curr1 != null && curr2 != null) {
          sum += curr1.value * curr2.value;
          curr1 = curr1.dir;
          curr2 = curr2.inf;
        }

        System.out.print(sum + " ");

        aux2 = aux2.dir;
      }

      System.out.println();

      aux1 = aux1.inf;
    }
  }
}

public class q11 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int qtde = sc.nextInt();
    for (int v = 0; v < qtde; v++) {
      int ln = sc.nextInt();
      int col = sc.nextInt();
      Matriz m1 = new Matriz(ln, col, sc);

      int ln2 = sc.nextInt();
      int col2 = sc.nextInt();
      Matriz m2 = new Matriz(ln2, col2, sc);

      m1.mostrarDiagonalPrincipal();
      m1.mostrarDiagonalSecundaria();
      Matriz.soma(m1, m2);
      Matriz.multiplicar(m1, m2);
    }

    sc.close();
  }
}