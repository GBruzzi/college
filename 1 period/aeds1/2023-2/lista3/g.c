#include<stdio.h>

int main() {
  int um4 = 0;
  int dois4 = 0;
  int tres4 = 0;
  int quatro4 = 0;

  int n = 1;
  int cont = 0;

  while (1) {
    printf("Entre com um numero entre 0 e 1 : [0 para parar]");
    scanf("%i", &n);

    if (n < 0 || n > 100)
      continue;
    
    if (n == 0)
      break;


    cont++;

    if (n <= 25) {
      um4++;
      continue;
    }
      

    if (n <= 50) {
      dois4++;
      continue;
    }
      

    if (n <= 75) {
      tres4++;
      continue;
    }
      

    quatro4++;
  }


  printf("\nvoce digitou %i valores. %i sao menores que 25, %i estao entre 25 e 50, %i estao entre 50 e 75, %i estao entre 75 e 100", cont,um4,dois4,tres4,quatro4);

  return 0;
}