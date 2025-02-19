import java.util.Scanner;

public class cesarRecursivo {

    public static String hashCesar(String mensagem, int chave) {
        return criptografarRecursivo(mensagem, chave, 0, new StringBuilder());
    }

    private static String criptografarRecursivo(String mensagem, int chave, int i, StringBuilder mensagemCifrada) {
        if (i >= mensagem.length()) {
            return mensagemCifrada.toString();
        }

        char c = mensagem.charAt(i);

        if (c >= 32 && c <= 126) { 
            char novaLetra = (char) (c + chave);
            if (novaLetra > 126) {
                novaLetra = (char) (novaLetra - 94); 
            }
            mensagemCifrada.append(novaLetra);
        } else {
            mensagemCifrada.append(c); 
        }

        return criptografarRecursivo(mensagem, chave, i + 1, mensagemCifrada);
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
