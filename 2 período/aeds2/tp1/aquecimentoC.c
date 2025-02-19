#include <stdio.h>
#include <string.h>

int contaM(char *s)
{
  int ans = 0;

  for (int i = 0; i < strlen(s); i++)
  {
    if (s[i] >= 'A' && s[i] <= 'Z')
      ans++;
  }

  return ans;
}

int main()
{
  char entrada[500];

  while (fgets(entrada, sizeof(entrada), stdin) != NULL)
  {

    entrada[strcspn(entrada, "\n")] = '\0';

    if (strcmp(entrada, "FIM") == 0)
    {
      break;
    }

    printf("%i\n", contaM(entrada));
  }

  return 0;
}