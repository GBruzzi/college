import java.util.Scanner;

public class Sort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int N = scanner.nextInt();
            int M = scanner.nextInt();

            if (N == 0 && M == 0) {
                break;
            }

            int[] numeros = new int[N];
            for (int i = 0; i < N; i++) {
                numeros[i] = scanner.nextInt();
            }

            // Bubble Sort personalizado
            for (int i = 0; i < N - 1; i++) {
                for (int j = 0; j < N - i - 1; j++) {
                    int moduloA = Math.abs(numeros[j] % M);
                    int moduloB = Math.abs(numeros[j + 1] % M);

                    if (moduloA != moduloB) {
                        if (moduloA > moduloB) {
                            int temp = numeros[j];
                            numeros[j] = numeros[j + 1];
                            numeros[j + 1] = temp;
                        }
                    } else if (numeros[j] % 2 != numeros[j + 1] % 2) {
                        if (numeros[j] % 2 == 0) { // Par após ímpar
                            int temp = numeros[j];
                            numeros[j] = numeros[j + 1];
                            numeros[j + 1] = temp;
                        }
                    } else if (numeros[j] % 2 == 0) { // Pares: menor antes do maior
                        if (numeros[j] > numeros[j + 1]) {
                            int temp = numeros[j];
                            numeros[j] = numeros[j + 1];
                            numeros[j + 1] = temp;
                        }
                    } else { // Ímpares: maior antes do menor
                        if (numeros[j] < numeros[j + 1]) {
                            int temp = numeros[j];
                            numeros[j] = numeros[j + 1];
                            numeros[j + 1] = temp;
                        }
                    }
                }
            }

            System.out.println(N + " " + M);
            for (int numero : numeros) {
                System.out.println(numero);
            }
            System.out.println("0 0");
        }
    }
}