#include <stdio.h>

void preencherVet(int *x, int *y) {
  

  for (int i = 0; i < 10; i ++) {
    x[i] = i;
    y[i] = 5 * i;
  }
}

void novoVet(int *x, int *y) {
  int novoVet[10];

  for (int i = 0; i < 10; i ++) {
    if (i % 2) {
      novoVet[i] = x[i];
      continue;
    }

    novoVet[i] = y[i];
  }

  for(int i = 0; i < 10; i ++) {
    printf("%i ", *(novoVet + i));
  }
}

int main() {
  int x[10];
  int y[10];

  preencherVet(x, y);
  novoVet(x, y);

  return 0;
}