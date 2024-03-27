#include <stdio.h>

int main() {
  printf("Entre com a idade : ");
  int idade;
  scanf("%i", &idade);

  if (idade < 16) {
    printf("\n voce nao pode ser eleitor");
  } else if (idade < 18) {
    printf("\n voce eh um eleitor facultativo menor de idade");
  } else if (idade < 64) {
    printf("\n voce eh um eleitor obrigatorio");
  } else {
    printf("\n voce eh um eleitor facultativo maior de idade");
  }

  return 0;
}