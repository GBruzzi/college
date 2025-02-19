public class Seis {
    public static boolean somenteVogal(String s) {
        char[] vogais = { 'a', 'e', 'i', 'o', 'u' };

        for (int i = 0; i < s.length(); i++) {
            char caractere = s.charAt(i);
            boolean ehVogal = false;

            for (int j = 0; j < vogais.length; j++) {
                if (caractere == vogais[j]) {
                    ehVogal = true;
                    break;
                }
            }

            if (!ehVogal) {
                return false;
            }
        }

        return true;
    }

    public static boolean somenteConsoantes(String s) {
        char[] vogais = { 'a', 'e', 'i', 'o', 'u' };

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < vogais.length; j++) {
                if (s.charAt(i) == vogais[j])
                    return false;
            }
        }

        return true;
    }

    public static boolean isInteger(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i == 0 && (c == '+' || c == '-')) {
                if (s.length() == 1) {
                    return false;
                }
                continue;
            }
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isReal(String s) {
        boolean hasDecimal = false;
        boolean hasDigit = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (i == 0 && (c == '+' || c == '-')) {
                if (s.length() == 1) {
                    return false;
                }
                continue;
            }

            if (c == '.') {
                if (hasDecimal) {
                    return false;
                }
                hasDecimal = true;
                continue;
            }

            if (Character.isDigit(c)) {
                hasDigit = true;
                continue;
            }

            return false;
        }

        return hasDigit;

    }

    public static void main(String[] args) {
        if (somenteVogal("aeiouasdasd")) {
            System.out.println("Somente vogal");
        } else {
            System.out.println("Nao eh somente vogal");
        }

        if (somenteConsoantes("aeiou")) {
            System.out.println("Somente consoante");
        } else {
            System.out.println("Nao eh somente consoante");
        }

        if (isInteger("aeiou")) {
            System.out.println("eh inteiro");
        } else {
            System.out.println("Nao eh inteiro");
        }

        if (isReal("aeiou")) {
            System.out.println("eh real");
        } else {
            System.out.println("Nao eh real");
        }

    }
}
