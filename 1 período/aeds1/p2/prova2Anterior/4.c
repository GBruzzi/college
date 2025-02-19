#include <stdio.h>
#include <string.h>

typedef struct {
  char nome[200];
  int dia,mes,ano;
}cliente;

typedef struct {
  cliente *cliente_informado;
  int b, a, c;
}conta;


cliente dados_pessoais(char *nome, int dia, int mes, int ano) {
  cliente novo_cliente;
  novo_cliente.dia = dia;
  novo_cliente.mes = mes;
  novo_cliente.ano = ano;
  strcpy(novo_cliente.nome, nome);

  return novo_cliente;
}


conta identifica (cliente *cliente_informado, int b, int a, int c) {
  conta nova_conta;
  nova_conta.cliente_informado = cliente_informado;
  nova_conta.b = b;
  nova_conta.a = a;
  nova_conta.c = c;

  return nova_conta;
}