#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

void alteracao(char *s, char c1, char c2) {
    for (int i = 0; i < strlen(s); i++) {
        if (s[i] == c1) {
            s[i] = c2;
        }
    }
}

int main() {
    srand(4); 

    char entrada[1000];
    while (fgets(entrada, sizeof(entrada), stdin)) {
        entrada[strcspn(entrada, "\n")] = '\0';

        char c1 = 'a' + rand() % 26;
        char c2 = 'a' + rand() % 26;

        while (c1 == c2) {
            c2 = 'a' + rand() % 26;
        }

        alteracao(entrada, c1, c2);
        printf("%s\n", entrada);
    }

    return 0;
}
