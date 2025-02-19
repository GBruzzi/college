#include <stdio.h>

int main() {
  printf("Entre com um numero : ");
  int n;
  scanf("%i", &n);

  int sum = 1;
  while (n > 0) {
    sum *= n;
    n--;
  }

  printf("\nO fatorial eh : %i", sum);


  return 0;
}