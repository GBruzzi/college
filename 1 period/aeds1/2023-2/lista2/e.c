#include <stdio.h>

int main() {
    int a, b, c;

    printf("Entre com o primeiro valor : ");
    scanf("%i", &a);

    printf("\nEntre com o segundo valor : ");
    scanf("%i", &b);

    printf("\nEntre com o terceiro valor : ");
    scanf("%i", &c);

    // Ordenando os valores
    if (a > b) {
        int temp = a;
        a = b;
        b = temp;
    }
    if (b > c) {
        int temp = b;
        b = c;
        c = temp;
    }
    if (a > b) {
        int temp = a;
        a = b;
        b = temp;
    }

    printf("Em ordem crescente: %i, %i, %i", a, b, c);

    return 0;
}
