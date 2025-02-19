import java.util.Scanner;

public class Cesar {

    public static String hashCesar(String mensagem, int chave) {
        StringBuilder mensagemCifrada = new StringBuilder();

        for (char c : mensagem.toCharArray()) {
            if (c >= 32 && c <= 126) { 
                char novaLetra = (char) (c + chave);
                if (novaLetra > 126) {
                    novaLetra = (char) (novaLetra - 94); 
                }
                mensagemCifrada.append(novaLetra);
            } else {
                mensagemCifrada.append(c); 
            }
        }

        return mensagemCifrada.toString();
    }

    public static String hashCesar(String mensagem) {
        return hashCesar(mensagem, 3);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String entrada = scanner.nextLine();
            if (entrada.equals("FIM")) 
                return;
            System.out.println(hashCesar(entrada));
        }
        scanner.close();
    }
}
