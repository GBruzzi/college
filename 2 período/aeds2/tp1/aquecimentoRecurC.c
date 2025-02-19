#include <stdio.h>
#include <string.h>

int contM(char* s, int index) {
  if (index == strlen(s))
    return 0;

  int count = 0;

  if (s[index] >= 'A' && s[index] <= 'Z')
    count++;

  return count + contM(s, index + 1);
}


int main() {
  char entrada[500];

  while (fgets(entrada, sizeof(entrada), stdin) != NULL)
  {
    entrada[strcspn(entrada, "\n")] = '\0';

    if (strcmp(entrada, "FIM") == 0) {
      break;
    }
    entrada[strcspn(entrada, "\n")] = '\0';
    printf("%i\n", contM(entrada, 0));
  }


  return 0;
}