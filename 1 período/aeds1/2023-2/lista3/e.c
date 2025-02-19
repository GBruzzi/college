#include <stdio.h>

int main() {
  int x,y;
  printf("Entre com o primeiro termo : ");
  scanf("%i", &x);
  printf("\nEntre com o segundo termo : ");
  scanf("%i", &y);

  int maior;

  if (x > y) 
    maior = x;
  else 
    maior = y;

  int mmc = maior;

  while (1) {
    if (mmc % x == 0 && mmc % y == 0) {
      break;
    }
    mmc += maior;
  }

  printf("\nO mmc eh %i", mmc);

  return 0;
}