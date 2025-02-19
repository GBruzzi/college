import java.util.*;

public class q1961 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt(); // número de palavras no dicionário
        int n = sc.nextInt(); // número de frases
        sc.nextLine(); // consome a nova linha

        String[] dict = new String[m];
        int[] valores = new int[m];

        // Leitura do dicionário
        for (int i = 0; i < m; i++) {
            dict[i] = sc.next(); // lê a palavra
            valores[i] = sc.nextInt(); // lê o valor correspondente
        }

        sc.nextLine(); // consome a nova linha

        // Processamento das frases
        for (int i = 0; i < n; i++) {
            StringBuilder input = new StringBuilder();
            int sum = 0; // soma reinicializada para cada frase

            while (true) {
                String line = sc.nextLine();
                
                // Adiciona a linha ao texto acumulado
                input.append(line).append("\n");
                
                // Verifica se a linha contém um ponto final para encerrar
                if (line.contains(".")) {
                    break;
                }
            }

            String completeInput = input.toString();
            // Divisão usando expressão regular para capturar todas as palavras
            String[] parts = completeInput.split("\\s+");

            // Soma os valores com base nas palavras
            for (String part : parts) {
                for (int o = 0; o < dict.length; o++) {
                    if (part.equals(dict[o])) { // comparação exata
                        sum += valores[o];
                        break; // Pare de buscar depois de encontrar
                    }
                }
            }

            System.out.println(sum); // Imprime a soma após processar a frase
        }

        sc.close(); // fecha o scanner
    }
}
