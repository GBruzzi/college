// erro

#include <stdio.h>

int fat(int n) {
  if (n == 1 || n == 0) 
    return 1;
  return n * fat(n - 1);
}

double serieS(int n) {
  if (n == 1)
    return 1.0;

  return 1.0 / fat(n) + serie(n - 1);

}


int main() {
  printf("insira o valor de n : ");
  int n;
  scanf("%i", &n);
  printf("O valor do somatorio eh %i", serieS(n));

  return 0;
}