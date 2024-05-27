#include <stdio.h>


int restDivisao(int n, int divisor) {
  if (n == divisor) {
    return 0;
  }

  if (n < divisor) {
    return n;
  }

  n -= divisor;

  return restDivisao(n, divisor);
}


int main() {
  printf("Insira um numero e o divisor : ");
  int n, divisor;
  scanf("%i %i", &n, &divisor);
  printf("\n O resto da divisao eh %i ", restDivisao(n, divisor));


  return 0;
}