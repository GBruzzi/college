#include <stdio.h>

void notas(int n1, int n2, int n3, char letra) {
  if (letra == 'A') {
    int media = (n1 + n2 + n3) / 3;
    printf("A media eh %i", media);
  }

  if (letra == 'P') {
    int media = ((n1 * 5) + (n2 * 3) + (n3 * 2)) / 10;
    printf("A media eh %i", media);
  }
}


int main() {
  notas(5,4,6,'P');

  return 0;
}