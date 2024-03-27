#include<stdio.h>

int main() {
  int x,y, temp;
  printf("Entre com o primeiro termo : ");
  scanf("%i", &x);
  printf("\nEntre com o segundo termo : ");
  scanf("%i", &y);

  while (y != 0 ) {
    temp = y;
    y = x % y;
    x = temp;
  }

  printf("\nO mdc eh %i", x);

  return 0;
}