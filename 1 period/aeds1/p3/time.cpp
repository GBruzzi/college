#include <iostream>
#include <string>

using namespace std;

// Definição da classe base Time
class Time
{
private:
  int titulos;    // Atributo privado para armazenar número de títulos
  string nome;    // Atributo privado para armazenar nome do time

public:
  int idade;      // Atributo público para armazenar idade do time

  // Método getter para obter o nome do time
  string getNome() {
    return nome;
  }

  // Método getter para obter o número de títulos do time
  int getTitulos() {
    return titulos;
  }

  // Método setter para definir o nome do time
  void setNome(string nome) {
    this->nome = nome;
  }

  // Método para simular um dia de jogo (definido fora da classe)
  void gameDay();

  // Método setter para adicionar títulos ao time
  void setTitle(int n);

  // Construtor padrão: inicializa o time com valores padrão
  Time() {
    nome = "cruzeiro";
    titulos = 15;
    idade = 101;
  }

  // Declaração de função teste (definida fora da classe)
  int teste(int n);

  // Construtor parametrizado: permite inicializar o time com valores específicos
  Time(string nome, int titulos, int idade);
};

// Implementação da função teste (definida fora da classe)
int Time::teste(int n) {
  return n * 10;
}

// Implementação do método setter setTitle (definido fora da classe)
void Time::setTitle(int n) {
  titulos += n;
}

// Implementação do método gameDay (definido fora da classe)
void Time::gameDay() {
  cout << "Hoje é dia de jogo!" << endl;
}

// Definição da classe derivada TimeDerivada que herda de Time
class TimeDerivada : public Time
{
private:
  string cidade;   // Atributo adicional para armazenar a cidade do time

public:
  // Método getter para obter a cidade do time
  string getCidade() {
    return cidade;
  }

  // Método setter para definir a cidade do time
  void setCidade(string cidade) {
    this->cidade = cidade;
  }

  // Método para simular um jogo importante (definido fora da classe)
  void jogoImportante();

  // Construtor parametrizado: inicializa o time derivado com valores específicos
  TimeDerivada(string nome, int titulos, int idade, string cidade) : Time(nome, titulos, idade) {
    this->cidade = cidade;
  }
};

// Implementação do método jogoImportante (definido fora da classe)
void TimeDerivada::jogoImportante() {
  cout << "Hoje tem jogo importante em " << cidade << "!" << endl;
}

// Função principal (main) onde o programa começa a execução
int main() {
  // Criação de um objeto da classe base Time usando o construtor padrão
  Time cruzeiro;
  cout << "Construtor padrão:\n";
  cout << "Idade: " << cruzeiro.idade << endl;            // Imprime idade do cruzeiro
  cout << "Nome: " << cruzeiro.getNome() << endl;          // Imprime nome do cruzeiro
  cout << "Títulos: " << cruzeiro.getTitulos() << endl;    // Imprime títulos do cruzeiro

  // Criação de um objeto da classe base Time usando o construtor parametrizado
  Time barcelona("barcelona", 89, 200);
  barcelona.setNome("real madrid");   // Define o nome do barcelona como "real madrid"
  cout << "Construtor com parâmetros:\n";
  cout << "Idade: " << barcelona.idade << endl;            // Imprime idade do barcelona
  cout << "Nome: " << barcelona.getNome() << endl;         // Imprime nome do barcelona
  cout << "Títulos: " << barcelona.getTitulos() << endl;   // Imprime títulos do barcelona

  // Aumenta o número de títulos do barcelona em 10
  barcelona.setTitle(10);
  cout << "Títulos após setTitle: " << barcelona.getTitulos() << endl;   // Imprime novos títulos do barcelona
  cout << barcelona.teste(10) << endl;  // Chama o método teste e imprime o resultado

  // Chama o método gameDay da classe base Time
  barcelona.gameDay();

  // Criação de um objeto da classe derivada TimeDerivada usando o construtor parametrizado
  TimeDerivada flamengo("flamengo", 50, 125, "Rio de Janeiro");
  cout << "Time derivado:\n";
  cout << "Nome: " << flamengo.getNome() << endl;           // Imprime nome do flamengo
  cout << "Títulos: " << flamengo.getTitulos() << endl;     // Imprime títulos do flamengo
  cout << "Idade: " << flamengo.idade << endl;              // Imprime idade do flamengo
  cout << "Cidade: " << flamengo.getCidade() << endl;       // Imprime cidade do flamengo

  // Define a cidade do flamengo como "São Paulo"
  flamengo.setCidade("São Paulo");
  cout << "Cidade alterada: " << flamengo.getCidade() << endl;   // Imprime nova cidade do flamengo

  // Chama o método jogoImportante da classe derivada TimeDerivada
  flamengo.jogoImportante();

  return 0;   // Retorna 0 para indicar sucesso na execução do programa
}
