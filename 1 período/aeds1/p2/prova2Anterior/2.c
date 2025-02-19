#include <stdio.h>
#include <string.h>

void substituir(char *vet, char a, char b) {
  for (int i = 0; i < strlen(vet); i ++) {
    if (vet[i] == a)
      vet[i] = b;
  }
}


int main() {
  char vet[100] = "oioioio";
  char a = 'i';
  char b = 'o';
  substituir(vet, a , b);
  printf("%s", vet);


  return 0;
}