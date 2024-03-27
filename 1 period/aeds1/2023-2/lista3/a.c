#include <stdio.h>

int main() {
  int sum = 0;
  int i;

  for (i = 0; i <= 1000; i ++) {
    sum += i;
  }

  printf("%i", sum);

  return 0;
}