#include <stdio.h>
typedef float fl;

void preencherVet(fl vet[]) {
  for (int i = 0; i < 5; i ++) {
    printf("entre com a nota %i : ", i + 1);
    scanf("%f", &vet[i]);
  }
}

void media(fl vet[]) {
  fl sum = 0;
  for (int i = 0; i < 5; i ++) {
    sum += vet[i];
  }

  fl media = sum / 5;
  int acima = 0;
  for (int i = 0; i < 5; i ++) {
    if (vet[i] > media)
      acima++;
  }

  printf("A media foi %f e %i estao acima dela", media, acima);
}


int main() {
  fl notas[5];
  preencherVet(notas);
  media(notas);

  return 0;

}