#include <stdio.h>

int main() {
  int matriz[4][4];

  for (int i = 0; i < 4; i ++) {
    for (int j = 0; j < 4; j ++) {
      matriz[i][j] = i + j;
    }
  }

  printf("matriz : \n");
  for (int i = 0; i < 4; i ++) {
    for (int j = 0; j < 4; j ++) {
      printf("%i ",matriz[i][j]);
    }
    printf("\n");
  }

  int sum = 0;
  for (int i = 0; i < 4; i ++) {
    for (int j = 0; j < 4; j ++) {
      if (i == j)
        sum += matriz[i][j];
    }
  }

  printf("soma diagonal : %i", sum);

  return 0;
}