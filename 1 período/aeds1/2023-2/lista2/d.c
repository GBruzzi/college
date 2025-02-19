#include <stdio.h>

int main() {
  double altura, peso;
  printf("Entre com a altura : ");
  scanf("%lf", &altura);

  printf("Entre com o peso : ");
  scanf("%lf", &peso);

  double imc = peso / (altura * altura);

  if (imc < 16) {
    printf("seu imc eh %.2lf e voce esta em hetcidade morbida", imc);
  } else if (imc <= 17) {
    printf("seu imc eh %.2lf e voce esta em hetcidade grave", imc);
  } else if (imc <= 18.5) {
    printf("seu imc eh %.2lf e voce esta abaixo do peso", imc);
  } else if (imc <= 25) {
    printf("seu imc eh %.2lf e voce esta no peso normal", imc);
  } else if (imc <= 30) {
    printf("seu imc eh %.2lf e voce esta em sobrepeso", imc);
  } else if (imc <= 35) {
    printf("seu imc eh %.2lf e voce esta em obesidade", imc);
  } else if (imc <= 40) {
    printf("seu imc eh %.2lf e voce esta em obesidade grave", imc);
  } else {
    printf("seu imc eh %.2lf e voce esta em obesidade mórbida", imc);
  } 

  return 0;
}