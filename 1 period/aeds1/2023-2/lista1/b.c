#include <stdio.h>
#include <math.h>

int main() {
  double areaBase, h;
  printf("Entre com a area da base : ");
  scanf("%lf", &areaBase);
  printf("\n Entre com a altura : ");
  scanf("%lf", &h);

  double volume = (1.0/3) * areaBase * h;
  printf("O volume é : %lf", volume);

  // faltando area superficial 

  return 0;
}