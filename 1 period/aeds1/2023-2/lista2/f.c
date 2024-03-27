#include <stdio.h>

int main()
{
  int dia;
  printf("Entre com o dia da semana : ");
  scanf("%i", &dia);

  switch (dia)
  {
  case 1:
    printf("\nHoje eh segunda");
    break;
  case 2:
    printf("\nHoje eh terca");
    break;
  case 3:
    printf("\nHoje eh quarta");
    break;
  case 4:
    printf("\nHoje eh quinta");
    break;
  case 5:
    printf("\nHoje eh sexta");
    break;
  case 6:
    printf("\nHoje eh sabado");
    break;
  case 7:
    printf("\nHoje eh domingo");
    break;
  default :
    printf("\nDia invalido");
    break;
  }

  return 0;
}