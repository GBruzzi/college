#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h>

// Estrutura que representa uma data
typedef struct Date {
    int day;    
    int month;  
    int year;   
} Date;

// Função que converte uma data em uma string no formato "DD/MM/AAAA"
char *dateToString(Date date) {
    char *str = (char *)malloc(11 * sizeof(char)); 
    sprintf(str, "%02d/%02d/%04d", date.day, date.month, date.year); 
    return str; 
}

// Função que converte uma string no formato "DD/MM/AAAA" em uma estrutura Date
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

// Estrutura que representa um Pokémon
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

// Função que duplica uma string alocando nova memória
char* my_strdup(const char* s) {
    char* copy = (char*)malloc(strlen(s) + 1); 

    if (copy != NULL) {
        strcpy(copy, s); 
    }
    return copy; 
}

// getters and setters

// id
int getId(Pokemon *p) {
    return p->id; 
}

void setId(Pokemon *p, int id) {
    p->id = id; 
}

// generation
int getGeneration(Pokemon *p) {
    return p->generation; 
}

void setGeneration(Pokemon *p, int generation) {
    p->generation = generation; 
}

// name
char* getName(Pokemon *p) {
    return p->name; 
}

void setName(Pokemon *p, char *name) {
    p->name = name; 
}

// description
char* getDescription(Pokemon *p) {
    return p->description; 
}

void setDescription(Pokemon *p, char *description) {
    p->description = description; 
}

// types
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

// abilities
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

// weight
double getWeight(Pokemon *p) {
    return p->weight; 
}

void setWeight(Pokemon *p, double weight) {
    p->weight = weight; 
}

// height
double getHeight(Pokemon *p) {
    return p->height; 
}

void setHeight(Pokemon *p, double height) {
    p->height = height; 
}

// captureRate
int getCaptureRate(Pokemon *p) {
    return p->captureRate; 
}

void setCaptureRate(Pokemon *p, int captureRate) {
    p->captureRate = captureRate; 
}

// isLegendary
bool getIsLegendary(Pokemon *p) {
    return p->isLegendary; 
}

void setIsLegendary(Pokemon *p, bool isLegendary) {
    p->isLegendary = isLegendary; 
}

// captureDate
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

    char *nameCopy = my_strdup(name);
    char *descriptionCopy = my_strdup(description);

    setName(&p, nameCopy);
    setDescription(&p, descriptionCopy);
    
    // types
    setTypes(&p, 0, type1);
    if (type2 != NULL) {
        setTypes(&p, 1, type2);
    }

    // abilities
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

// splitar CSV
int split_csv_line(char *line, char **fields, int max_fields) {
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
    // Adiciona o último campo
    if (field_count < max_fields) {
        fields[field_count++] = field_start;
    }

    return field_count;
}

// ler os pokemons
void lerPokemon(FILE *file, Pokemon *pokedex, int *n) {
    char line[1024];

    fgets(line, sizeof(line), file); // Lê o cabeçalho do CSV

    while (fgets(line, sizeof(line), file) != NULL) {
        line[strcspn(line, "\n")] = '\0'; // Remove a nova linha

        Pokemon p;
        memset(&p, 0, sizeof(Pokemon)); //zera a memoria 

        char *fields[12]; 
        int field_count = split_csv_line(line, fields, 12);

        // id
        p.id = atoi(fields[0]);

        // generation
        p.generation = atoi(fields[1]);

        // name
        p.name = my_strdup(fields[2]);

        // description
        p.description = my_strdup(fields[3]);
        
        // types
        setTypes(&p, 0, fields[4]);
        if (strlen(fields[5]) > 0) {
            setTypes(&p, 1, fields[5]);
        } else {
            strcpy(p.types[1], "");
        }

        // abilities
        char *abilities_field = fields[6];
        // remove double quotes
        if (abilities_field[0] == '"' && abilities_field[strlen(abilities_field) - 1] == '"') {
            abilities_field[strlen(abilities_field) - 1] = '\0';
            abilities_field++;
        }
        // remove colchetes
        if (abilities_field[0] == '[' && abilities_field[strlen(abilities_field) - 1] == ']') {
            abilities_field[strlen(abilities_field) - 1] = '\0';
            abilities_field++;
        }

        // divide nas abilities individuais
        char *abilityToken;
        char *restAbilities = abilities_field;
        int abilityIndex = 0;
        while ((abilityToken = strtok_r(restAbilities, ",", &restAbilities)) && abilityIndex < 6) {
            // remove simple quotes
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
        // Preenche o restante das habilidades com strings vazias
        for (; abilityIndex < 6; abilityIndex++) {
            strcpy(p.abilities[abilityIndex], "");
        }

        // weight
        p.weight = atof(fields[7]);

        // height
        p.height = atof(fields[8]);

        // captureRate
        p.captureRate = atoi(fields[9]);

        // isLegendary
        p.isLegendary = atoi(fields[10]);

        // captureDate
        p.captureDate = stringToDate(fields[11]);
        
        pokedex[*n] = p;
        (*n)++;
    }
}

void imprimirPokemon(Pokemon *p) {
    printf("[#%d -> %s: %s - ['", getId(p), getName(p), getDescription(p));

    // types
    int numTypes = getNumTypes(p);
    if(numTypes > 0) {
        printf("%s", getTypes(p, 0));
    }
    if (numTypes > 1) {
        printf("', '%s", getTypes(p, 1));
    }
    printf("'] - ");

    // abilities
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

int main () {
    char *csvPath = "/tmp/pokemon.csv";
    FILE *file = fopen(csvPath, "r");   

    if (file == NULL) {
        printf("Erro ao abrir o arquivo CSV.\n");
        return 1;
    } 

    Pokemon pokedex[801];
    int n = 0;

    lerPokemon(file, pokedex, &n);

    fclose(file);

    char inputId[10];
    scanf("%s", inputId); // Limita a entrada para evitar overflow
    while (strcmp(inputId, "FIM") != 0) {
        int id = atoi(inputId);
        bool foundPokemon = false;
        for (int i = 0 ; i < n ; i++) {
            if (pokedex[i].id == id) {
                imprimirPokemon(&pokedex[i]);
                foundPokemon = true;
                break;
            }
        }
        
        scanf("%s", inputId); // Limita a entrada novamente
    }

    // Libera a memória alocada
    for (int i = 0; i < n; i++) {
        free(pokedex[i].name);
        free(pokedex[i].description);
    }

    return 0;
}