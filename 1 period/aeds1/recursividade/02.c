#include <stdio.h>

int somaDigit(int n) {
  if (n == 0) {
    return 0;
  }

  return (n % 10) + somaDigit(n/10);
}

int main() {
  printf("Insira um numero : ");
  int n;
  scanf("%i", &n);
  printf("\n A soma eh %i ", somaDigit(n));


  return 0;
}