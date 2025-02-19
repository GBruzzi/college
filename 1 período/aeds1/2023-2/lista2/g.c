#include <stdio.h>

int main() {
  double salario;
  printf("Entre com seu salario : ");
  scanf("%lf", &salario); 

  double imposto;

  if (salario < 2112) {
    imposto = 0;
  } else if (salario < 2826.65) {
    imposto = salario * 0.075;
  } else if (salario < 2826.65) {
    imposto = salario * 0.15;
  } else if (salario < 2826.65) {
    imposto = salario * 0.225;
  } else {
    imposto = salario * 0.275;
  }

  printf("\nSeu imposto vai ser %.2lf", imposto);

  return 0;
}