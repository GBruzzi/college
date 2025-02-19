#include <stdio.h>

int main() {
  int n;
  printf("Entre com um numero inteiro : ");
  scanf("%i", &n);

  if (n % 2 == 0) {
    printf("Esse numero eh divisivel por 2,");
  }

  if (n % 5 == 0) {
    printf("esse numero eh divisivel por 5,");
  }

  if (n % 10 == 0) {
    printf("esse numero eh divisivel por 10.");
  }

  if (n % 2 != 0 && n % 5 != 0 && n % 10 != 0) {
    printf("esse numero nao eh divisivel por nenhum.");
  }

  return 0;
}