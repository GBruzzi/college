#include <stdlib.h>

float* soma_vetores(float* v1, int tam1, float* v2, int tam2, int* tam_soma) {

    int maior_tamanho = (tam1 > tam2) ? tam1 : tam2;
    *tam_soma = maior_tamanho;


    float* soma = (float*)malloc(maior_tamanho * sizeof(float));
    if (soma == NULL) {
        return NULL;
    }


    for (int i = 0; i < maior_tamanho; i++) {
        float valor1 = (i < tam1) ? v1[i] : 0;
        float valor2 = (i < tam2) ? v2[i] : 0;
        soma[i] = valor1 + valor2;
    }

    return soma;
}