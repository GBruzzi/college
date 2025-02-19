#include <stdio.h>

int main() {
  int n;
  printf("Entre com um numero inteiro ");
  scanf("%i", &n);

  if (n % 2 == 0) {
    printf("%i Eh um numero par", n);
    return 0;
  }

  printf("%i eh um numero impar", n);

  return 0;
}