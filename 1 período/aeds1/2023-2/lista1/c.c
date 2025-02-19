#include <stdio.h>
#include <math.h>

int main() {
  double cateto1, cateto2;
  printf("Entre com o valor do cateto1 : ");
  scanf("%lf", &cateto1);

  printf("\n Entre com o valor do cateto2 : ");
  scanf("%lf", &cateto2);

  double hip = sqrt((cateto1 * cateto1) + (cateto2 * cateto2));
  printf("\n A hipotenusa é %.2lf", hip);

  double area = (cateto1 * cateto2) / 2;
  printf("\n a area eh : %.2lf", area);

  double p = cateto1 + cateto2 + hip;
  printf("\n O perimetro eh : %.2lf", p);

  double a1, a2;
  a1 = acos(cateto1 / hip);
  a1 = a1 * (180 / 3.14);
  a2 = 90 - a1;

  printf("\n Os valores dos outros dois angulos sao : %.2lf e %.2lf", a1,a2);

  return 0;
}