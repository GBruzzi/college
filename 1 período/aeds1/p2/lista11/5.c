#include <stdio.h>
#include <stdlib.h>

int** aloca (int n, int m) {
  int **matriz = (int**)malloc(n * sizeof(int*));

  if (matriz == NULL)
    return -1;

  for (int i = 0; i < n; i ++) {
    matriz[i] = (int*)malloc(m * sizeof(int));
    if (matriz[i] == NULL) {
      for (int j = 0; j < i; j ++) {
        free(matriz[j]);
      }

      free(matriz);
      return NULL;
    } 

  for (int j = 0; j < m; j ++) {
    matriz[i][j] = i + j;
  }
      
  }

  return matriz;

}

