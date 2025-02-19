#include <stdio.h>
#include <string.h>

int main() {
    int k, n;
    scanf("%d %d", &k, &n);

    char alfabeto[k + 1];
    char enviada[n + 1];

    scanf("%s", alfabeto);
    scanf("%s", enviada);

    int teste = 1;  // Vamos assumir que a string enviada está correta até provado o contrário

    for (int i = 0; i < n; i++) {
        int encontrado = 0;

        for (int j = 0; j < k; j++) {
            if (enviada[i] == alfabeto[j]) {
                encontrado = 1;
                break;
            }
        }

        if (!encontrado) {
            teste = 0;
            break;
        }
    }

    if (teste) {
        printf("S\n");
    } else {
        printf("N\n");
    }

    return 0;
}
