#include <stdio.h>





int main() {
  int x = 10;
  int *ptrX = &x;

  printf("%d\n", x);
  printf("%p\n", &x);
  printf("%p\n", ptrX);
  printf("%d", *ptrX);

  return 0;
}