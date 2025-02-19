#include <stdio.h>

int main()
{
  int x, y, temp;
  printf("Entre com o primeiro termo : ");
  scanf("%i", &x);
  printf("\nEntre com o segundo termo : ");
  scanf("%i", &y);

  int n1 = x;
  int n2 = y;
  while (y != 0) {
    temp = y;
    y = x % y;
    x = temp;
  }

  if (x == 1) {
    printf("os numeros %i e %i sao primos entre si", n1,n2);
  } else {
    printf("os numeros %i e %i nao sao primos entre si", n1,n2);
  }

  return 0;
}