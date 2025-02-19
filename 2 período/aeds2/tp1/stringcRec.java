import java.util.Scanner;

public class stringcRec {

    public static boolean isVogal(char c) {
        c = Character.toLowerCase(c);
        return "aeiou".indexOf(c) != -1;
    }

    public static boolean isConsoante(char c) {
        return Character.isLetter(c) && !isVogal(c);
    }

    public static boolean isInteiro(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isReal(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean verificarVogais(String s, int i) {
        if (i >= s.length()) return true;
        return isVogal(s.charAt(i)) && verificarVogais(s, i + 1);
    }

    public static boolean verificarConsoantes(String s, int i) {
        if (i >= s.length()) return true;
        return isConsoante(s.charAt(i)) && verificarConsoantes(s, i + 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            if (linha.equals("FIM"))
                return;
            
            boolean vogais = verificarVogais(linha, 0);
            boolean consoantes = verificarConsoantes(linha, 0);
            boolean inteiro = isInteiro(linha);
            boolean real = isReal(linha);

            System.out.printf("%s %s %s %s%n",
                    vogais ? "SIM" : "NAO",
                    consoantes ? "SIM" : "NAO",
                    inteiro ? "SIM" : "NAO",
                    real ? "SIM" : "NAO");
        }

        scanner.close();
    }
}

