#include <stdio.h>

int main() {

  int n;
  int sum = 0;
  int cont = -1;
  while (n != 0) {
    printf("Entre com o valor de n : [0 para parar] \n");
    scanf("%i", &n);
    sum += n;
    cont++;
  }
  int media = sum / cont;
  printf("A media eh %i", media);

  return 0;
}