// https://judge.beecrowd.com/pt/problems/view/1766

import java.util.*;

class Renas {
    String nome;
    int peso;
    int idade;
    float altura;

    Renas(String nome, int peso, int idade, float altura) {
        this.nome = nome;
        this.peso = peso;
        this.idade = idade;
        this.altura = altura;
    }
}

public class q1766 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt(); // lê o número de casos de teste
        sc.nextLine(); // consome a nova linha após ler t

        for (int i = 0; i < t; i++) {
            int n = sc.nextInt(); // lê n
            int m = sc.nextInt(); // lê m
            sc.nextLine(); // consome a nova linha após ler m

            Renas[] vet = new Renas[n];
            // Leitura dos dados dos participantes
            for (int j = 0; j < n; j++) {
                String input = sc.nextLine();
                String[] separadas = input.split(" ");
                int peso = Integer.parseInt(separadas[1]);
                int idade = Integer.parseInt(separadas[2]);
                float altura = Float.parseFloat(separadas[3]);

                vet[j] = new Renas(separadas[0], peso, idade, altura);

            }

            ordena(vet);

            for (int j = 0; j < m; j ++) {
                System.out.println(vet[j].nome);
            }

        }

        sc.close(); // fecha o Scanner
    }

    static void ordena(Renas vet[]) {
        for (int i = 1; i < vet.length; i++) {
            Renas key = vet[i];
            int actual = i;
    
            // O loop continua enquanto o índice atual for maior que 0
            // e as condições de ordenação forem satisfeitas.
            while (actual > 0 && 
                   (vet[actual - 1].peso < key.peso ||  // Peso decrescente
                   (vet[actual - 1].peso == key.peso && vet[actual - 1].idade > key.idade) ||  // Idade crescente
                   (vet[actual - 1].peso == key.peso && vet[actual - 1].idade == key.idade && vet[actual - 1].altura > key.altura) ||  // Altura crescente
                   (vet[actual - 1].peso == key.peso && vet[actual - 1].idade == key.idade && vet[actual - 1].altura == key.altura && vet[actual - 1].nome.compareTo(key.nome) > 0))) {  // Nome crescente
                vet[actual] = vet[actual - 1];
                actual--;
            }
    
            vet[actual] = key;
        }
    }
    
    
}
