#include <stdio.h>

void printIdade(int *n) {
  printf("%i", *n);
  *n = 100;
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

  

  return 0;
}