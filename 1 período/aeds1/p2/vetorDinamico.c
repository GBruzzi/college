#include <stdio.h>
#include <stdlib.h>


int main() {
  int tam;
  printf("digite o tamanho do vetor ");
  scanf("%i", &tam);

  int *vet = (int*)malloc(tam * sizeof(int));

  if (vet == NULL)
    return -1;

  for (int i = 0; i < tam; i ++) {
    vet[i] = i * 5;
  }

  for (int i = 0; i < tam; i ++) {
    printf("%i ", *(vet + i));
  }


  free(vet);
  return 0;
}