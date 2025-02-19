#include <stdio.h>
#include <string.h>

int main() {
  int op;
  scanf("%d", &op);
  int pilha[10000];
  int n = 0;


  for (int i = 0; i < op; i ++) {
    char consulta[10000000];
    scanf(" %[^\n]", consulta);

    if (!strcmp(consulta, "POP")) {
      if (n == 0) continue;

      pilha[n - 1] = 0;
      n--;
    } else if (!strcmp(consulta, "MIN")) {
      int menor = 99999999;

      for (int k = 0; k < n; k ++) {
        if (pilha[k] < menor) {
          menor = pilha[k];
        }
      } 
      printf("%d", menor);
    } else {
      char numero[1000000];
      int cont = 0;
      for (int h = 5; h < strlen(consulta); h ++) {
        numero[cont] = consulta[h];
      }

      int valor = atoi(numero);

      pilha[n] = valor;
      n ++;
    }
  }

  return 0;
}