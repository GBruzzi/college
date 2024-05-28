#include <stdio.h>
#include <string.h>

int verificaStr(char *str1, char *str2) {
  int res = 0;
  for (int i = 0; i < strlen(str1); i ++) {
    if (str1[i] == str2[0]) {
      int verifica = 1;
      int j;
      for (j = 1; j < strlen(str2);j ++) {
        if (str2[j] != str1[i + j]) {
          verifica = 0;
        }
      }

      if (verifica) {
        i += j;
        res++;
      }
        
    }
  }

  return res;
}


int main() {
  char str1[100] = "abcdabcydfysdfyhsydfabc";
  char str2[100] = "abc";

  printf("%i", verificaStr(str1, str2));

  return 0;
}