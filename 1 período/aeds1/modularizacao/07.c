#include <stdio.h>

int verifica(int n)
{
  if (n < 0)
    return 0;
  return 1;
}

int main()
{
  printf("entre com um numero : ");
  int n;
  scanf("%i", &n);

  for (int i = 0; i < n; i++)
  {
    printf("\n entre com n numeros : ");
    int number;
    scanf("%i", &number);

    if (verifica(number))
    {
      printf("o numero %i eh positivo \n", number);
      continue;
    }
    printf("o numero %i eh negativo \n", number);
  }

  return 0;
}