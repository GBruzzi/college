#include <stdio.h>

typedef struct {
  int h, min, sec;
} horas;

typedef struct {
  int dia, mes , ano
} data;


typedef struct {
  data data_inf;
  horas horas_inf;
} tempo;

typedef struct {
  char nome[500];
  float preco;
  int quant;
  tempo tempo_inf;
}prod;

int main() {
  tempo hoje;
  hoje.data_inf.dia = 22;
  hoje.data_inf.mes = 5;
  hoje.data_inf.ano = 2024;

  hoje.horas_inf.h = 20;
  hoje.horas_inf.min = 60;
  hoje.horas_inf.sec = 58;



  return 0;
}