#include <iostream>
#include <string>

using namespace std;


class Time
{
private:
  int titulos;    
  string nome;    

public:
  int idade;     
  string getNome() {
    return nome;
  }

  int getTitulos() {
    return titulos;
  }

  void setNome(string nome) {
    this->nome = nome;
  }

  void gameDay();

  void setTitle(int n);

  Time() {
    nome = "cruzeiro";
    titulos = 15;
    idade = 101;
  }

  int teste(int n);

  Time(string nome, int titulos, int idade);
};

int Time::teste(int n) {
  return n * 10;
}

void Time::setTitle(int n) {
  titulos += n;
}

void Time::gameDay() {
  cout << "Hoje é dia de jogo!" << endl;
}

class TimeDerivada : public Time
{
private:
  string cidade;   

public:
  string getCidade() {
    return cidade;
  }

  void setCidade(string cidade) {
    this->cidade = cidade;
  }

  void jogoImportante();

  TimeDerivada(string nome, int titulos, int idade, string cidade) : Time(nome, titulos, idade) {
    this->cidade = cidade;
  }
};

void TimeDerivada::jogoImportante() {
  cout << "Hoje tem jogo importante em " << cidade << "!" << endl;
}

int main() {
  Time cruzeiro;
  cout << "Construtor padrão:\n";
  cout << "Idade: " << cruzeiro.idade << endl;            
  cout << "Nome: " << cruzeiro.getNome() << endl;          
  cout << "Títulos: " << cruzeiro.getTitulos() << endl;    

  Time barcelona("barcelona", 89, 200);
  barcelona.setNome("real madrid");   
  cout << "Construtor com parâmetros:\n";
  cout << "Idade: " << barcelona.idade << endl;            
  cout << "Nome: " << barcelona.getNome() << endl;         
  cout << "Títulos: " << barcelona.getTitulos() << endl;   

  barcelona.setTitle(10);
  cout << "Títulos após setTitle: " << barcelona.getTitulos() << endl;   
  cout << barcelona.teste(10) << endl;  

  barcelona.gameDay();

  TimeDerivada flamengo("flamengo", 50, 125, "Rio de Janeiro");
  cout << "Time derivado:\n";
  cout << "Nome: " << flamengo.getNome() << endl;           
  cout << "Títulos: " << flamengo.getTitulos() << endl;     
  cout << "Idade: " << flamengo.idade << endl;              
  cout << "Cidade: " << flamengo.getCidade() << endl;       

  flamengo.setCidade("São Paulo");
  cout << "Cidade alterada: " << flamengo.getCidade() << endl;   

  flamengo.jogoImportante();

  return 0;   
}
