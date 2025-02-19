#include <stdio.h>

int contaDigit(int n) {
    if (n == 0) {
        return 0;  
    }

    return 1 + contaDigit(n / 10); 
}

int main() {
  printf("Insira um numero : ");
  int n;
  scanf("%i", &n);
  printf("\n O numero tem %i digitos", contaDigit(n));


  return 0;
}