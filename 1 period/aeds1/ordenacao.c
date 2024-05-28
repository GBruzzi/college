#include <stdio.h>

void ordena(int vet[], int index) {
    int menor = vet[index];
    int menorIndex = index;
    int temp;
    
    // Encontra o menor elemento na sublista
    for (int i = index + 1; i < 5; i++) {
        if (vet[i] < menor) {
            menor = vet[i];
            menorIndex = i;
        }
    }
    
    if (menorIndex != index) {
        temp = vet[index];
        vet[index] = vet[menorIndex];
        vet[menorIndex] = temp;
    }
}

int main() {
    int notas[] = {5, 8, 3, 1, 7};
    

    for (int i = 0; i < 5; i++) {
        ordena(notas, i);
    }
    
    for (int i = 0; i < 5; i++) {
        printf("%i ", notas[i]);
    }
    
    return 0;
}
