#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

typedef struct Date {
    int day;
    int month;
    int year;
} Date;

char *dateToString(Date date) {
    char *str = (char *)malloc(11 * sizeof(char));
    sprintf(str, "%02d/%02d/%04d", date.day, date.month, date.year);
    return str;
}

Date stringToDate(char *str) {
    Date date;
    if (str != NULL && strlen(str) > 0) { 
        sscanf(str, "%d/%d/%d", &date.day, &date.month, &date.year);
    } else {
        date.day = 0;
        date.month = 0;
        date.year = 0;
    }
    return date;
}

typedef struct Pokemon {
    int id;
    int generation;
    char *name;
    char *description;
    char types[2][50];
    char abilities[6][50];
    double weight;
    double height;
    int captureRate;
    bool isLegendary;
    Date captureDate;
} Pokemon;

char* strdup(const char* s) {
    char* copy = (char*)malloc(strlen(s) + 1);

    if (copy != NULL) {
        strcpy(copy, s);
    }
    return copy;
}

int getId(Pokemon *p) {
    return p->id;
}

void setId(Pokemon *p, int id) {
    p->id = id;
}

int getGeneration(Pokemon *p) {
    return p->generation;
}

void setGeneration(Pokemon *p, int generation) {
    p->generation = generation;
}

char* getName(Pokemon *p) {
    return p->name;
}

void setName(Pokemon *p, char *name) {
    p->name = name;
}

char* getDescription(Pokemon *p) {
    return p->description;
}

void setDescription(Pokemon *p, char *description) {
    p->description = description;
}

char* getTypes(Pokemon *p, int index) {
    return p->types[index];
}

int getNumTypes(Pokemon *p) {
    int count = 0;
    for (int i = 0; i < 2; i++) {
        if (strlen(p->types[i]) > 0) {
            count++;
        }
    }
    return count;
}

void setTypes(Pokemon *p, int index, char *type) {
    strncpy(p->types[index], type, sizeof(p->types[index]) - 1);
    p->types[index][sizeof(p->types[index]) - 1] = '\0';
}

char* getAbilities(Pokemon *p, int index) {
    return p->abilities[index];
}

int getNumAbilities(Pokemon *p) {
    int count = 0;
    for (int i = 0; i < 6; i++) {
        if (strlen(p->abilities[i]) > 0) {
            count++;
        }
    }
    return count;
}

void setAbilities(Pokemon *p, int index, const char *ability) {
    strncpy(p->abilities[index], ability, sizeof(p->abilities[index]) - 1);
    p->abilities[index][sizeof(p->abilities[index]) - 1] = '\0';
}

double getWeight(Pokemon *p) {
    return p->weight;
}

void setWeight(Pokemon *p, double weight) {
    p->weight = weight;
}

double getHeight(Pokemon *p) {
    return p->height;
}

void setHeight(Pokemon *p, double height) {
    p->height = height;
}

int getCaptureRate(Pokemon *p) {
    return p->captureRate;
}

void setCaptureRate(Pokemon *p, int captureRate) {
    p->captureRate = captureRate;
}

bool getIsLegendary(Pokemon *p) {
    return p->isLegendary;
}

void setIsLegendary(Pokemon *p, bool isLegendary) {
    p->isLegendary = isLegendary;
}

Date getCaptureDate(Pokemon *p) {
    return p->captureDate;
}

void setCaptureDateDate(Pokemon *p, Date captureDate) {
    p->captureDate = captureDate;
}

void setCaptureDateString(Pokemon *p, char *captureDate) {
    p->captureDate = stringToDate(captureDate);
}

Pokemon createPokemon(int id, int generation, char *name,
    char *description, char *type1, char *type2, char *abilities[6], double weight,
    double height, int captureRate, bool isLegendary, Date captureDate) {

    Pokemon p;

    setId(&p, id);
    setGeneration(&p, generation);

    char *nameCopy = strdup(name);
    char *descriptionCopy = strdup(description);

    setName(&p, nameCopy);
    setDescription(&p, descriptionCopy);
    
    setTypes(&p, 0, type1);
    if (type2 != NULL) {
        setTypes(&p, 1, type2);
    }

    for (int i = 0; i < 6; i++) {
        if (abilities[i] != NULL) {
            setAbilities(&p, i, abilities[i]);
        } else {
            strcpy(p.abilities[i], "");
        }
    }

    setWeight(&p, weight);
    setHeight(&p, height);
    setCaptureRate(&p, captureRate);
    setIsLegendary(&p, isLegendary);
    setCaptureDateDate(&p, captureDate);

    return p;
}

int split_csv(char *line, char **fields, int max_fields) {
    int field_count = 0;
    char *ptr = line;
    int in_quotes = 0;
    char *field_start = ptr;

    while (*ptr && field_count < max_fields) {
        if (*ptr == '"') {
            in_quotes = !in_quotes;
        } else if (*ptr == ',' && !in_quotes) {
            *ptr = '\0';
            fields[field_count++] = field_start;
            field_start = ptr + 1;
        }
        ptr++;
    }
    if (field_count < max_fields) {
        fields[field_count++] = field_start;
    }

    return field_count;
}

void preencherPok(FILE *file, Pokemon *pokedex, int *n) {
    char line[1024];

    fgets(line, sizeof(line), file); 

    while (fgets(line, sizeof(line), file) != NULL) {
        line[strcspn(line, "\n")] = '\0'; 

        Pokemon p;
        memset(&p, 0, sizeof(Pokemon)); 

        char *fields[12]; 
        int field_count = split_csv(line, fields, 12);
        p.id = atoi(fields[0]);
        p.generation = atoi(fields[1]);
        p.name = strdup(fields[2]);
        p.description = strdup(fields[3]);
        
        setTypes(&p, 0, fields[4]);
        if (strlen(fields[5]) > 0) {
            setTypes(&p, 1, fields[5]);
        } else {
            strcpy(p.types[1], "");
        }

        char *abilities_field = fields[6];

        if (abilities_field[0] == '"' && abilities_field[strlen(abilities_field) - 1] == '"') {
            abilities_field[strlen(abilities_field) - 1] = '\0';
            abilities_field++;
        }

        if (abilities_field[0] == '[' && abilities_field[strlen(abilities_field) - 1] == ']') {
            abilities_field[strlen(abilities_field) - 1] = '\0';
            abilities_field++;
        }

        char *abilityToken;
        char *restAbilities = abilities_field;
        int abilityIndex = 0;
        while ((abilityToken = strtok_r(restAbilities, ",", &restAbilities)) && abilityIndex < 6) {

            while (*abilityToken == ' ' || *abilityToken == '\'') abilityToken++;
            char *tempEnd = abilityToken + strlen(abilityToken) - 1;
            while (tempEnd > abilityToken && (*tempEnd == ' ' || *tempEnd == '\'')) {
                *tempEnd = '\0';
                tempEnd--;
            }
            if (strlen(abilityToken) > 0) {
                setAbilities(&p, abilityIndex, abilityToken);
                abilityIndex++;
            }
        }

        for (; abilityIndex < 6; abilityIndex++) {
            strcpy(p.abilities[abilityIndex], "");
        }

        p.weight = atof(fields[7]);
        p.height = atof(fields[8]);
        p.captureRate = atoi(fields[9]);
        p.isLegendary = atoi(fields[10]);
        p.captureDate = stringToDate(fields[11]);
        
        pokedex[*n] = p;
        (*n)++;
    }
}

void printPok(Pokemon *p) {
    printf("[#%d -> %s: %s - ['", getId(p), getName(p), getDescription(p));
    int numTypes = getNumTypes(p);
    if(numTypes > 0) {
        printf("%s", getTypes(p, 0));
    }
    if (numTypes > 1) {
        printf("', '%s", getTypes(p, 1));
    }
    printf("'] - ");

    int numAbilities = getNumAbilities(p);
    printf("[");
    for (int i = 0 ; i < numAbilities ; i++) {
        printf("'%s'", getAbilities(p, i));
        if (i < numAbilities - 1) {
            printf(", ");
        }
    }
    printf("] - ");

    printf("%.1fkg - ", getWeight(p));
    printf("%.1fm - ", getHeight(p));
    printf("%d%% - ", getCaptureRate(p));
    printf("%s - ", getIsLegendary(p) ? "true" : "false");
    printf("%d gen] - ", getGeneration(p));
    char *data = dateToString(getCaptureDate(p));
    printf("%s", data);
    free(data);

    printf("\n");
}

Pokemon* findPokemonById(Pokemon pokedex[], int n, int id) {
    for (int i = 0; i < n; i++) {
        if (pokedex[i].id == id) {
            return &pokedex[i];
        }
    }
    return NULL;
}

Pokemon* findPokemonByName(Pokemon pokedex[], int n, char name[]) {
    for (int i = 0 ; i < n ; i++) {
        if (strcmp(pokedex[i].name, name) == 0) {
            return &pokedex[i];
        }
    }
    return NULL;
}

typedef struct Celula {
    Pokemon *pokemon;
    struct Celula* prox;
} Celula;

Celula* novaCelula(Pokemon *pokemon) {
    Celula* nova = (Celula*) malloc(sizeof(Celula));
    nova->pokemon = pokemon;
    nova->prox = NULL;
    return nova;
}

typedef struct Lista {
    Celula* primeiro;
    Celula* ultimo;
    int n;
} Lista;

void inicializarLista(Lista *lista) {
    lista->primeiro = NULL;
    lista->ultimo = NULL;
    lista->n = 0;
}

void inserirInicio(Lista* lista, Pokemon* p) {
    Celula* tmp = novaCelula(p);
    tmp->prox = lista->primeiro;
    lista->primeiro = tmp;
    if (lista->ultimo == NULL) {
        lista->ultimo = tmp;
    }
}

void inserirFim(Lista* lista, Pokemon* p) {
    Celula* tmp = novaCelula(p);
    if (lista->primeiro == NULL) {
        lista->primeiro = tmp;
        lista->ultimo = tmp;
    } else {
        lista->ultimo->prox = tmp;
        lista->ultimo = tmp;
    }
}

void inserir(Lista* lista, Pokemon* p, int pos) {
    if (pos == 0) {
        inserirInicio(lista, p);
    } else if (pos == lista->n) {
        inserirFim(lista, p);
    } else {
        Celula *i = lista->primeiro;
        for (int count = 0 ; count < pos - 1 ; count++) {
            i = i->prox;
        }
        Celula* tmp = novaCelula(p);
        tmp->prox = i->prox;
        i->prox = tmp;
        lista->n++;
    }
}

bool pesquisar(Lista* lista, Pokemon* p, int pos) {
    bool resp = false;
    Celula* i = lista->primeiro;
    while (i != NULL) {
        if (i->pokemon == p) {
            printf("(Posicao: %i) SIM\n", pos);
            resp = true;
            i = lista->ultimo;
        }
        i = i->prox;
    }
    if (!resp) {
        printf("NAO\n");
    }

    return resp;
}

typedef struct Hash {
    Lista** tabela;
    int tam;
} Hash;

Hash* inicializarHash(Hash *h, int tam) {
    h->tam = tam;
    h->tabela = (Lista**) malloc(tam * sizeof(Lista*));
    for (int i = 0 ; i < tam ; i++) {
        h->tabela[i] = (Lista*) malloc(sizeof(Lista));
        inicializarLista(h->tabela[i]);
    }
}

int calcularHash(Pokemon* p, int tam) {
    int sum = 0;
    char* name = p->name;
    for (int i = 0 ; i < strlen(name) ; i++) {
        sum += (int) name[i];
    }

    return sum % tam;
}

void inserirHash(Hash *h, Pokemon* p) {
    int pos = calcularHash(p, h->tam);
    Lista* lista = h->tabela[pos];
    inserirInicio(lista, p);
}

bool pesquisarHash(Hash* h, Pokemon* p) {
    int pos = calcularHash(p, h->tam);
    Lista* lista = h->tabela[pos];
    return pesquisar(lista, p, pos);
}

int main () {
    char *csvPath = "/tmp/pokemon.csv";

    FILE *file = fopen(csvPath, "r");   

    if (file == NULL) {
        printf("erro ao abrir csv.\n");
        return 1;
    } 

    Pokemon pokedex[801];
    int n = 0;

    preencherPok(file, pokedex, &n);

    fclose(file);

    clock_t start = clock();

    Hash hash; 
    inicializarHash(&hash, 21);

    char inputId[10];
    scanf("%s", inputId);
    
    while (strcmp(inputId, "FIM") != 0) {
        int id = atoi(inputId);
        Pokemon* p = findPokemonById(pokedex, n, id);
        if(p != NULL) {
            inserirHash(&hash, p);
        }
        
        scanf("%s", inputId); 
    }

    char name[20];
    scanf("%s", name);
    while(strcmp(name, "FIM") != 0) {
        Pokemon* p = findPokemonByName(pokedex, n, name);
        printf("=> %s: ", name);

        pesquisarHash(&hash, p);

        scanf("%s", name);
    }

    clock_t end = clock();

    double executionTime = ((double)(end - start)) / CLOCKS_PER_SEC * 1000.0;

    FILE *arquivo = fopen("813584_hash.txt", "w");
    if (arquivo == NULL) {
        printf("Erro ao abrir o arquivo!\n");
        return 1;
    }

    fprintf(arquivo, "813584\t%.2f\n", executionTime);
    fclose(arquivo);

    for (int i = 0; i < n; i++) {
        free(pokedex[i].name);
        free(pokedex[i].description);
    }

    return 0;
}