import java.util.Scanner;

public class q2150 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String vogaisAlienigenas = sc.nextLine();
            if (vogaisAlienigenas.isEmpty()) {
                break; 
            }

            String frase = sc.nextLine();

            int contador = 0;

            for (int i = 0; i < frase.length(); i++) {
                for (int j = 0; j < vogaisAlienigenas.length(); j++) {
                    if (frase.charAt(i) == vogaisAlienigenas.charAt(j)) {
                        contador++;
                        break;
                    }
                }
            }

            System.out.println(contador);
        }

        sc.close();
    }
}
