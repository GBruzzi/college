// https://judge.beecrowd.com/pt/problems/view/1766

#include <stdio.h>

struct rena {
  char nome[100];
  int peso;
  int idade;
  float altura;
};


void ordenar(struct rena* vet, int n) {
    for (int i = 1; i < n; i++) {
        struct rena key = vet[i];
        int j = i - 1;

        // Ordena de acordo com os critérios especificados
        while (j >= 0 && 
               (vet[j].peso < key.peso ||  // Peso decrescente
               (vet[j].peso == key.peso && vet[j].idade > key.idade) ||  // Idade crescente
               (vet[j].peso == key.peso && vet[j].idade == key.idade && vet[j].altura > key.altura) ||  // Altura crescente
               (vet[j].peso == key.peso && vet[j].idade == key.idade && vet[j].altura == key.altura && strcmp(vet[j].nome, key.nome) > 0))) {  // Nome crescente
            vet[j + 1] = vet[j];  // Move o elemento para a direita
            j--;
        }
        vet[j + 1] = key;  // Insere o elemento na posição correta
    }
}

int main() {
  int t; scanf("%i", &t);

  while (t--) {
    int n; scanf("%i", &n);
    int m; scanf("%i", &m);

    struct rena vet[n];

    for (int i = 0; i < n; i ++) {
      scanf("%s %i %i %f", vet[i].nome, &vet[i].peso, &vet[i].idade, &vet[i].altura);
    }

    ordenar(vet, n);

    for (int i = 0; i < m; i ++) {
      printf("%s\n", vet[i].nome);
    }
  }

  return 0;
}