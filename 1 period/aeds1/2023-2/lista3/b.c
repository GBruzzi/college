#include <stdio.h>
#include <math.h>

int main() {
  int n;
  printf("Entre com o numero de termos : ");
  scanf("%i", &n);

  double sum = 0;
  int i;
  for (i = 0; i <= n; i ++) {
    sum += 1.0 / (pow(2,i));
  }

  printf("\n %lf", sum);

  return 0;
}