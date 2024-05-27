#include <stdio.h>

int collatz(int n) {
  if (n == 1)
    return 0;


  if (n % 2) 
    return 1 + collatz(3 * n + 1);


  return 1 + collatz(n / 2);
}


void triangle(int n) {
  for (int i = 0; i < n; i ++) {
    for (int j = n - i; j > 0; j--) {
      printf("*");
    }
    printf("\n");
  }
}

int segundos(int h, int m, int s) {
  return h * 3600 + m * 60 + s;
}


int main() {
  printf("%i", collatz(84));
  // triangle(5);
  // printf("%i", segundos(1,1,0));
}