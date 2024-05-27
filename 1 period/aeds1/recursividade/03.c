#include <stdio.h>


int divisaoDiff(int n, int divisor) {
  if (n < divisor) {
    return 0;
  }

  return 1 + divisaoDiff(n - divisor, divisor);
}


int main() {
  printf("Insira um numero e o divisor : ");
  int n, divisor;
  scanf("%i %i", &n, &divisor);
  printf("\n A divisao eh %i ", divisaoDiff(n, divisor));


  return 0;
}