import java.util.Arrays;
import java.util.Random;

public class QuickSortVariations {
    static long operacoes;

    public static void quickSortFirstPivot(int[] array, int inicio, int fim) {
        if (inicio < fim) {
            int posPivo = particionarPrimeiro(array, inicio, fim);
            quickSortFirstPivot(array, inicio, posPivo - 1);
            quickSortFirstPivot(array, posPivo + 1, fim);
        }
    }

    public static void quickSortLastPivot(int[] array, int inicio, int fim) {
        if (inicio < fim) {
            int posPivo = particionarUltimo(array, inicio, fim);
            quickSortLastPivot(array, inicio, posPivo - 1);
            quickSortLastPivot(array, posPivo + 1, fim);
        }
    }

    public static void quickSortRandomPivot(int[] array, int inicio, int fim) {
        if (inicio < fim) {
            int posPivo = particionarAleatorio(array, inicio, fim);
            quickSortRandomPivot(array, inicio, posPivo - 1);
            quickSortRandomPivot(array, posPivo + 1, fim);
        }
    }

    public static void quickSortMedianOfThree(int[] array, int inicio, int fim) {
        if (inicio < fim) {
            int posPivo = particionarMedianaTres(array, inicio, fim);
            quickSortMedianOfThree(array, inicio, posPivo - 1);
            quickSortMedianOfThree(array, posPivo + 1, fim);
        }
    }

    private static int particionarPrimeiro(int[] array, int inicio, int fim) {
        int pivo = array[inicio];
        int i = inicio + 1;

        for (int j = inicio + 1; j <= fim; j++) {
            operacoes++; 
            if (array[j] < pivo) {
                trocar(array, i, j);
                i++;
            }
        }

        trocar(array, inicio, i - 1);
        return i - 1;
    }

    private static int particionarUltimo(int[] array, int inicio, int fim) {
        int pivo = array[fim];
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            operacoes++; 
            if (array[j] < pivo) {
                i++;
                trocar(array, i, j);
            }
        }

        trocar(array, i + 1, fim);
        return i + 1;
    }

    private static int particionarAleatorio(int[] array, int inicio, int fim) {
        Random random = new Random();
        int indicePivo = random.nextInt(fim - inicio + 1) + inicio;
        trocar(array, indicePivo, fim);  
        return particionarUltimo(array, inicio, fim);  
    }

    private static int particionarMedianaTres(int[] array, int inicio, int fim) {
        int meio = (inicio + fim) / 2;
        int mediana = medianaDeTres(array, inicio, meio, fim);
        trocar(array, mediana, fim);  
        return particionarUltimo(array, inicio, fim);
    }

    private static int medianaDeTres(int[] array, int inicio, int meio, int fim) {
        operacoes += 3; 
        if (array[inicio] > array[meio]) trocar(array, inicio, meio);
        if (array[inicio] > array[fim]) trocar(array, inicio, fim);
        if (array[meio] > array[fim]) trocar(array, meio, fim);
        return meio;  
    }

    private static void trocar(int[] array, int i, int j) {
        operacoes++; 
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println("Testes com arrays de 100 elementos:");
        testarTodosOsSorts(100);
        System.out.println("\nTestes com arrays de 10.000 elementos:");
        testarTodosOsSorts(10000);
    }

    public static void testarTodosOsSorts(int tamanho) {
        int[] arrayOrdenado = gerarArrayOrdenado(tamanho);
        int[] arrayQuaseOrdenado = gerarArrayQuaseOrdenado(tamanho);
        int[] arrayAleatorio = gerarArrayAleatorio(tamanho);

        System.out.printf("%-30s | %-15s\n", "Tipo de Array - Método", "Operações");
        System.out.println("---------------------------------------------");

        testarSorts(arrayOrdenado.clone(), "Ordenado - Primeiro Pivô");
        testarSorts(arrayOrdenado.clone(), "Ordenado - Último Pivô");
        testarSorts(arrayOrdenado.clone(), "Ordenado - Mediana de Três");
        testarSorts(arrayOrdenado.clone(), "Ordenado - Aleatório");

        testarSorts(arrayQuaseOrdenado.clone(), "Quase Ordenado - Primeiro Pivô");
        testarSorts(arrayQuaseOrdenado.clone(), "Quase Ordenado - Último Pivô");
        testarSorts(arrayQuaseOrdenado.clone(), "Quase Ordenado - Mediana de Três");
        testarSorts(arrayQuaseOrdenado.clone(), "Quase Ordenado - Aleatório");

        testarSorts(arrayAleatorio.clone(), "Aleatório - Primeiro Pivô");
        testarSorts(arrayAleatorio.clone(), "Aleatório - Último Pivô");
        testarSorts(arrayAleatorio.clone(), "Aleatório - Mediana de Três");
        testarSorts(arrayAleatorio.clone(), "Aleatório - Aleatório");
    }

    public static void testarSorts(int[] array, String metodo) {
        operacoes = 0; 
        switch (metodo) {
            case "Ordenado - Primeiro Pivô":
                quickSortFirstPivot(array, 0, array.length - 1);
                break;
            case "Ordenado - Último Pivô":
                quickSortLastPivot(array, 0, array.length - 1);
                break;
            case "Ordenado - Mediana de Três":
                quickSortMedianOfThree(array, 0, array.length - 1);
                break;
            case "Ordenado - Aleatório":
                quickSortRandomPivot(array, 0, array.length - 1);
                break;
            case "Quase Ordenado - Primeiro Pivô":
                quickSortFirstPivot(array, 0, array.length - 1);
                break;
            case "Quase Ordenado - Último Pivô":
                quickSortLastPivot(array, 0, array.length - 1);
                break;
            case "Quase Ordenado - Mediana de Três":
                quickSortMedianOfThree(array, 0, array.length - 1);
                break;
            case "Quase Ordenado - Aleatório":
                quickSortRandomPivot(array, 0, array.length - 1);
                break;
            case "Aleatório - Primeiro Pivô":
                quickSortFirstPivot(array, 0, array.length - 1);
                break;
            case "Aleatório - Último Pivô":
                quickSortLastPivot(array, 0, array.length - 1);
                break;
            case "Aleatório - Mediana de Três":
                quickSortMedianOfThree(array, 0, array.length - 1);
                break;
            case "Aleatório - Aleatório":
                quickSortRandomPivot(array, 0, array.length - 1);
                break;
        }
        System.out.printf("%-30s | %-15d\n", metodo, operacoes);
    }

    public static int[] gerarArrayOrdenado(int tamanho) {
        int[] array = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            array[i] = i + 1; 
        }
        return array;
    }

    public static int[] gerarArrayQuaseOrdenado(int tamanho) {
        int[] array = gerarArrayOrdenado(tamanho);
        Random random = new Random();
        for (int i = 0; i < tamanho / 10; i++) { 
            int j = random.nextInt(tamanho);
            int k = random.nextInt(tamanho);
            trocar(array, j, k);
        }
        return array;
    }

    public static int[] gerarArrayAleatorio(int tamanho) {
        int[] array = new int[tamanho];
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            array[i] = random.nextInt(tamanho); 
        }
        return array;
    }
}
