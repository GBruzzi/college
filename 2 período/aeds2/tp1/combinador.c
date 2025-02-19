#include <stdio.h>
#include <string.h>

void comb(char *s1, char *s2, char *resultado) {
    int i, j = 0;
    int len1 = strlen(s1);
    int len2 = strlen(s2);
    int maxLength = len1 > len2 ? len1 : len2;

    for (i = 0; i < maxLength; i++) {
        if (i < len1) {
            resultado[j++] = s1[i];  // Adiciona caractere de s1
        }
        if (i < len2) {
            resultado[j++] = s2[i];  // Adiciona caractere de s2
        }
    }
    resultado[j] = '\0';  // Finaliza a string
}

int main() {
    char s1[100], s2[100], resultado[200];

    while (1) {
        if (scanf("%99s %99s", s1, s2) != 2) {
            break;  
        }

        comb(s1, s2, resultado);

        printf("%s\n", resultado);
    }

    return 0;
}
