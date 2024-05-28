#include <stdio.h>

void maiorMenor(int vet[], int tam, int *menor, int *maior) {
  for (int i = 1; i < tam; i ++) {
    if (vet[i] < *menor) {
      *menor = vet[i];
      continue;
    }

    if (vet[i] > *maior)
      *maior = vet[i];
  }
}


int main() {
  int vet[] = {5,6,22,77,1,6,7};
  int maior = vet[0];
  int menor = vet[0];

  maiorMenor(vet,7,&menor, &maior);
  printf("maior : %i menor : %i", maior, menor);

  return 0;
}