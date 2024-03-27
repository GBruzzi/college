#include <stdio.h>

int main(){
  

  printf("Entre com um numero : ");
  int n;
  scanf("%i", &n);
  int primo = 1;
  int i;
  for (i = 2; i < n; i ++) {
    if (n % i == 0) {
      primo = 0;
      break;
    }
  }

  if (!primo) {
    printf("\nO numero %i nao eh primo", n);
  } else {
    printf("\nO numero %i eh primo", n);
  }

  return 0;
}