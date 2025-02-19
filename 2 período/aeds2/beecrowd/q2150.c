#include <stdio.h>
#include <string.h>

int main() {
  char vogais[100];
  char frase[1000];

  while (scanf(" %[^\n]",vogais) == 1) {
    scanf(" %[^\n]", frase);

    int cont = 0;
    for (int i =0;i < strlen(frase); i ++) {
      for (int j = 0;j < strlen(vogais); j ++) {
        if (frase[i] == vogais[j]) {
          cont++;
          break;
        }
      }
    }

    printf("%i\n", cont);
  }


  return 0;
}