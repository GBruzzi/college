// https://judge.beecrowd.com/pt/problems/view/1382
#include <stdio.h>

int selectionModified(int* vet, int tam) {
  int trocas = 0;
  for (int i = 0; i < tam - 1; i ++) {
    int menor = i;

    for (int j = i + 1; j < tam; j ++) {
      if (vet[j] < vet[menor]) {
        menor = j;
      }
    }

    if (menor != i) {
      trocas++;
      int tmp = vet[menor];
      vet[menor] = vet[i];
      vet[i] = tmp;
    }
  }

  return trocas;
}

int main() {
  int t; scanf("%i", &t);

  while ( t--) {
    int tam; scanf("%i", &tam);
    int vet[tam];

    for (int i = 0; i < tam; i ++) {
      scanf("%i", &vet[i]);
    }

    printf("%i\n", selectionModified(vet, tam));
  }

  return 0;
}