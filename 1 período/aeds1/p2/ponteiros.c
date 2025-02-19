#include <stdio.h>

void printIdade(int *n) {
  printf("%i", *n);
  *n = 100;
}

void imprimir (int vet[], int n) {
  for (int i = 0; i < n; i ++) {
    printf("%i ", vet[i]);
  }
  printf("\n");
}

void modifica(int vet[], int n) {
  for (int i = 0; i < n; i ++) {
    vet[i] = vet[i] * 2;
  }
}

int main() {
  // int n = 10;
  // int *nP;
  // nP = &n;

  // printf("valor de n : %i\n", n);
  // printf("endereco de memoria de n : %p", &n);

  // printf("\nvalor de n : %i\n", *nP);
  // printf("endereco de memoria de n : %p", nP);

  //passagem de parametro por ponteiro
  // int idade = 200;
  // printIdade(&idade);
  // printf("\nidade novamente : %i", idade);

  int notas[5] = {12,21,5,20,10};
  imprimir(notas, 5);
  modifica(notas, 5);
  imprimir(notas, 5);
  return 0;
}