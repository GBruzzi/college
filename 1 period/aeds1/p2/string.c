#include <stdio.h>
#include <string.h>

int main()
{
  char nome[200] = "guil";
  char novastr[100];

  for (int i = 0; i < strlen(nome); i++)
  {
    printf("%c", *(nome + i));
  }

  int j = 0;
  for (int i = strlen(nome) - 1; i >= 0; i--)
  {
    novastr[j] = nome[i];
    j++;
  }

  novastr[j ] = '\0';
  printf("\n");
  printf("%s", novastr);

  return 0;
}