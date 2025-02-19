import java.util.Scanner;

public class boolRec {
  private static String substituirValores(int[] variaveis, String expressao) {
    String novaExpressao = "";

    for (int i = 0; i < expressao.length(); i++) {
      if (expressao.charAt(i) != ' ') {
        if (expressao.charAt(i) == 'A')
          novaExpressao += variaveis[0];
        else if (expressao.charAt(i) == 'B')
          novaExpressao += variaveis[1];
        else if (expressao.charAt(i) == 'C')
          novaExpressao += variaveis[2];
        else
          novaExpressao += expressao.charAt(i);
      }
    }

    return novaExpressao;
  }

  private static String aplicarNot(String expressao) {
    String novaExpressao = "";

    for (int i = 0; i < expressao.length(); i++) {
      if (expressao.charAt(i) == 'n' && expressao.charAt(i + 2) == 't' &&
          (expressao.charAt(i + 4) == '1' || expressao.charAt(i + 4) == '0')) {
        novaExpressao += expressao.charAt(i + 4) == '1' ? 0 : 1;
        i += 5;
      } else
        novaExpressao += expressao.charAt(i);
    }

    return novaExpressao;
  }

  private static String aplicarAnd(String expressao) {
    String novaExpressao = "";

    for (int i = 0; i < expressao.length(); i++) {
      if (expressao.charAt(i) == 'a' && expressao.charAt(i + 1) == 'n') {
        if (expressao.charAt(i + 7) == ')') {
          novaExpressao += (expressao.charAt(i + 4) == '1' && expressao.charAt(i + 6) == '1') ? '1' : '0';
          i += 7;
        }
        else if (expressao.charAt(i + 9) == ')') {
          novaExpressao += (expressao.charAt(i + 4) == '1' && expressao.charAt(i + 6) == '1' && expressao.charAt(i + 8) == '1') ? '1' : '0';
          i += 9;
        }
        else if (expressao.charAt(i + 11) == ')') {
          novaExpressao += (expressao.charAt(i + 4) == '1' && expressao.charAt(i + 6) == '1' && expressao.charAt(i + 8) == '1' && expressao.charAt(i + 10) == '1') ? '1' : '0';
          i += 11;
        }
        else
          novaExpressao += expressao.charAt(i);
      } else
        novaExpressao += expressao.charAt(i);
    }

    return novaExpressao;
  }

  private static String aplicarOr(String expressao) {
    String novaExpressao = "";

    for (int i = 0; i < expressao.length(); i++) {
      if (expressao.charAt(i) == 'o' && expressao.charAt(i + 1) == 'r') {
        if (expressao.charAt(i + 6) == ')') {
          novaExpressao += (expressao.charAt(i + 3) == '1' || expressao.charAt(i + 5) == '1') ? '1' : '0';
          i += 6;
        }
        else if (expressao.charAt(i + 8) == ')') {
          novaExpressao += (expressao.charAt(i + 3) == '1' || expressao.charAt(i + 5) == '1' || expressao.charAt(i + 7) == '1') ? '1' : '0';
          i += 8;
        }
        else if (expressao.charAt(i + 10) == ')') {
          novaExpressao += (expressao.charAt(i + 3) == '1' || expressao.charAt(i + 5) == '1' || expressao.charAt(i + 7) == '1' || expressao.charAt(i + 9) == '1') ? '1' : '0';
          i += 10;
        }
        else
          novaExpressao += expressao.charAt(i);
      } else
        novaExpressao += expressao.charAt(i);
    }

    return novaExpressao;
  }

  private static String avaliarExpressaoBooleanaRecursiva(String expressao, int indice) {
    if (indice >= expressao.length() - 1) {
        return expressao;
    }
    
    char atual = expressao.charAt(indice);
    String resultadoParcial;

    if (atual == 'n') {
        resultadoParcial = aplicarNot(avaliarExpressaoBooleanaRecursiva(expressao, indice + 4));
    } else if (atual == 'o') {
        resultadoParcial = aplicarOr(avaliarExpressaoBooleanaRecursiva(expressao, indice + 3));
    } else if (atual == 'a') {
        resultadoParcial = aplicarAnd(avaliarExpressaoBooleanaRecursiva(expressao, indice + 4));
    } else {
        return avaliarExpressaoBooleanaRecursiva(expressao, indice + 1);
    }

    return resultadoParcial;
}

private static String avaliarExpressaoBooleana(String expressao) {
    return avaliarExpressaoBooleanaRecursiva(expressao, 0);
}


  public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);

    int quantidadeVariaveis = entrada.nextInt();
    String expressao;
    int[] valoresVariaveis = new int[3];

    while (quantidadeVariaveis != 0) {
      for (int i = 0; i < quantidadeVariaveis; i++)
        valoresVariaveis[i] = entrada.nextInt();
      expressao = entrada.nextLine();

      expressao = substituirValores(valoresVariaveis, expressao);

      expressao = avaliarExpressaoBooleana(expressao);

      System.out.println(expressao);

      quantidadeVariaveis = entrada.nextInt();
    }

    entrada.close();
  }
}
