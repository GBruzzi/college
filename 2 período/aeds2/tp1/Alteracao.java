import java.util.Random;
import java.util.Scanner;

public class Alteracao {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rdm = new Random();
        rdm.setSeed(4);

        while (scanner.hasNextLine()) {
            String entrada = scanner.nextLine();
            char c1 = (char) ('a' + rdm.nextInt(26));
            char c2 = (char) ('a' + rdm.nextInt(26));

            while (c1 == c2) {
                c2 = (char) ('a' + rdm.nextInt(26));
            }

            String resultado = alteracao(entrada, c1, c2);
            System.out.println(resultado);
        }
        scanner.close();
    }
    public static String alteracao(String s, char c1, char c2) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c1) {
                ans.append(c2);
            } else {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }
}
