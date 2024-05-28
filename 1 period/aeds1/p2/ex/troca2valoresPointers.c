#include <stdio.h>

void troca(int *n1, int *n2) {
  int temp = *n1;
  *n1 = *n2;
  *n2 = temp;
}


int main() {

  int n1 = 20;
  int n2 = 10;
  troca(&n1, &n2);
  printf("%i  %i", n1, n2);

  return 0;
}