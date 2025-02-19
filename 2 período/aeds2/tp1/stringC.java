import java.util.Scanner;

public class stringC {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            if (linha.equals("FMI"))
                return;
            boolean vogais = true, consoantes = true, inteiro = false, real = false;

            if (!linha.isEmpty()) {
                for (char c : linha.toCharArray()) {
                    vogais &= isVogal(c);
                    consoantes &= isConsoante(c);
                }
                inteiro = isInteiro(linha);
                real = isReal(linha);
            }

            System.out.printf("%s %s %s %s%n",
                    vogais ? "SIM" : "NAO",
                    consoantes ? "SIM" : "NAO",
                    inteiro ? "SIM" : "NAO",
                    real ? "SIM" : "NAO");
        }

        scanner.close();
    }
}