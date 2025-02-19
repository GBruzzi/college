#include <stdio.h>
#include <string.h>


int main() {
  int n;
  scanf("%d", &n);

  char pokemos[n][1000];
  int cont = 0;
  
  while (n--) {
    char pok[1000];
    scanf("%s", pok);
    int present = 0;

    for (int i = 0;i < cont; i ++) {
      if (!strcmp(pok, pokemos[i])) {
        present = 1;
        break;
      }
    }

    if (!present) {
      strcpy(pokemos[cont], pok);
      cont++;
    }

  }

  int res = 151 - cont;
  printf("Falta(m) ");
  printf("%d", res);
  printf(" pomekon(s).\n");
  return 0;
}