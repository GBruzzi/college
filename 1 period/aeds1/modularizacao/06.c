#include <stdio.h>

int fat(int n) {
  int cont = 1;
  for (int i = n; i > 1; i --) {
    cont *= i;
  }

  return cont;
}

double soma(int n) {
  double s = 1.0;
  for (int i = 1; i <= n; i ++) {
    s += 1.0 / fat(i);
  }

  return s;
}



int main() {
  printf("entre com o valor de n : ");
  int n;
  scanf("%i", &n);
  printf("o valor da soma eh %.2lf", soma(n));

  return 0;
}