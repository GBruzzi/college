import java.util.Scanner;

public class algebraBool {
  private static String substituirValores(int[] entradas, String expressao) {
    String novaExpressao = "";

    for (int i = 0; i < expressao.length(); i++) {
      if (expressao.charAt(i) != ' ') {
        if (expressao.charAt(i) == 'A')
          novaExpressao += entradas[0];
        else if (expressao.charAt(i) == 'B')
          novaExpressao += entradas[1];
        else if (expressao.charAt(i) == 'C')
          novaExpressao += entradas[2];
        else
          novaExpressao += expressao.charAt(i);
      }
    }

    return novaExpressao;
  }

  private static String aplicarNot(String expressao) {
    String novaExpressao = "";

    for (int i = 0; i < expressao.length(); i++) {
      if (expressao.charAt(i) == 'n' && (expressao.charAt(i + 4) == '1' || expressao.charAt(i + 4) == '0')) {
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
      if (expressao.charAt(i) == 'a' &&
          (expressao.charAt(i + 4) == '1' || expressao.charAt(i + 4) == '0') &&
          (expressao.charAt(i + 6) == '1' || expressao.charAt(i + 6) == '0')) {
        if (expressao.charAt(i + 7) == ')') {
          novaExpressao += (expressao.charAt(i + 4) == '1' && expressao.charAt(i + 6) == '1') ? 1 : 0;
          i += 7;
        } else if (expressao.charAt(i + 9) == ')') {
          novaExpressao += (expressao.charAt(i + 4) == '1' && expressao.charAt(i + 6) == '1' && expressao.charAt(i + 8) == '1') ? 1 : 0;
          i += 9;
        } else if (expressao.charAt(i + 11) == ')') {
          novaExpressao += (expressao.charAt(i + 4) == '1' && expressao.charAt(i + 6) == '1' && expressao.charAt(i + 8) == '1' && expressao.charAt(i + 10) == '1') ? 1 : 0;
          i += 11;
        } else {
          novaExpressao += expressao.charAt(i);
        }
      } else
        novaExpressao += expressao.charAt(i);
    }

    return novaExpressao;
  }

  private static String aplicarOr(String expressao) {
    String novaExpressao = "";

    for (int i = 0; i < expressao.length(); i++) {
      if (expressao.charAt(i) == 'o' && expressao.charAt(i + 1) == 'r' &&
          (expressao.charAt(i + 3) == '1' || expressao.charAt(i + 3) == '0') &&
          (expressao.charAt(i + 5) == '1' || expressao.charAt(i + 5) == '0')) {
        if (expressao.charAt(i + 6) == ')') {
          novaExpressao += (expressao.charAt(i + 3) == '1' || expressao.charAt(i + 5) == '1') ? 1 : 0;
          i += 6;
        } else if (expressao.charAt(i + 8) == ')'){
          novaExpressao += (expressao.charAt(i + 3) == '1' || expressao.charAt(i + 5) == '1' || expressao.charAt(i + 7) == '1') ? 1 : 0;
          i += 8;
        } else if (expressao.charAt(i + 10) == ')') {
          novaExpressao += (expressao.charAt(i + 3) == '1' || expressao.charAt(i + 5) == '1' || expressao.charAt(i + 7) == '1' || expressao.charAt(i + 9) == '1') ? 1 : 0;
          i += 10;
        }
      } else
        novaExpressao += expressao.charAt(i);
    }

    return novaExpressao;
  }

  private static String resolverAlgebraBooleana(String expressao) {
    int numOperacoes = 0;

    for (int i = 0; i < expressao.length(); i++)
      if (expressao.charAt(i) == '(')
        numOperacoes++;

    char[] pilhaOperacoes = new char[numOperacoes];

    for (int i = 0; i < expressao.length() - 2; i++)
      if (expressao.charAt(i + 2) == 't') 
        pilhaOperacoes[--numOperacoes] = 'n';
      else if (expressao.charAt(i + 2) == 'd') 
        pilhaOperacoes[--numOperacoes] = 'a';
      else if (expressao.charAt(i + 1) == 'r') 
        pilhaOperacoes[--numOperacoes] = 'o';

    for (int i = 0; i < pilhaOperacoes.length; i++) {
      if (pilhaOperacoes[i] == 'a')
        expressao = aplicarAnd(expressao);
      else if (pilhaOperacoes[i] == 'n')
        expressao = aplicarNot(expressao);
      else
        expressao = aplicarOr(expressao);
    }

    return expressao;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int qtdeEntradas = scanner.nextInt();
    String expressao;
    int[] entradas = new int[3];

    while (qtdeEntradas != 0) {
      for (int i = 0; i < qtdeEntradas; i++)
        entradas[i] = scanner.nextInt();
      expressao = scanner.nextLine();

      expressao = substituirValores(entradas, expressao);

      expressao = resolverAlgebraBooleana(expressao);

      System.out.println(expressao);

      qtdeEntradas = scanner.nextInt();
    }

    scanner.close();
  }
}
