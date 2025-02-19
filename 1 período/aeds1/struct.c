#include <stdio.h>
#include <string.h>

typedef struct {
  char nome[100];
  int idade;
  char sexo;
}pessoa;

int main() {
  pessoa pessoa1;
  // atribuicao antes
  // pessoa1.idade = 20;
  // pessoa1.sexo = 'F';
  // strcpy(pessoa1.nome, "julia");

  fgets(pessoa1.nome, 100, stdin);
  scanf("%i", &pessoa1.idade);
  scanf("%c"); // tirar o buffer de \n do teclado
  scanf("%c", &pessoa1.sexo);
  
  printf("Idade : %i\n", pessoa1.idade);
  printf("Sexo : %c\n", pessoa1.sexo);
  printf("Nome : %s\n", pessoa1.nome);

  return 0;
}