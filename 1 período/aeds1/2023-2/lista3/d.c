#include <stdio.h>

int main() {
  int n;
  printf("Entre com o numero de termos da fibo : ");
  scanf("%i", &n);

  int menor = 0;
  int maior = 1;

  int i;
  printf("%i ", menor);

  for (i = 0; i < n - 1; i ++) {
    printf("%i ", maior);
    
    int temp = menor;
    menor = maior;
    maior += temp;

  }

  return 0;
}