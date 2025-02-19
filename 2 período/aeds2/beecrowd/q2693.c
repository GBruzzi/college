// https://judge.beecrowd.com/pt/problems/view/2693

#include <stdio.h>
#include <string.h>

struct pessoa {
    char nome[100];
    char regiao;
    int distancia;
};


void ordenaVet(struct pessoa* vet, int n) {
  for (int i = 1; i < n; i ++) {
    struct pessoa key = vet[i];
    int actual = i;

    while ((actual > 0) && (vet[actual - 1].distancia > key.distancia) ||
    (vet[actual - 1].distancia == key.distancia && vet[actual - 1].regiao > key.regiao) || (vet[actual - 1].distancia == key.distancia && vet[actual - 1].regiao == key.regiao && vet[actual - 1].nome < key.nome)
    ) {
      vet[actual] = vet[actual - 1];
      actual--;
    }

    vet[actual] = key;
  }
}


int main() {
  int n; scanf("%i", &n);
  struct pessoa arr[n];

  for (int i = 0; i < n; i++) {
        scanf("%s %c %d", arr[i].nome, &arr[i].regiao, &arr[i].distancia);
  }

  ordenaVet(arr, n);

  for (int i = 0; i < n; i ++) {
    printf("%s\n", arr[i].nome);
  }

  return 0;
}