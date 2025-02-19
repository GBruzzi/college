import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.Scanner;

public class arquivo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        String fileName = "values.dat";

        try (RandomAccessFile file = new RandomAccessFile(fileName, "rw")) {
            for (int i = 0; i < n; i++) {
                double value = scanner.nextDouble();
                file.writeDouble(value);
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
            return;
        }

        try (RandomAccessFile file = new RandomAccessFile(fileName, "r")) {
            long fileLength = file.length();
            int sizeOfDouble = Double.BYTES;
            for (int i = 0; i < n; i++) {
                file.seek(fileLength - sizeOfDouble);
                double value = file.readDouble();
                System.out.println(value);
                fileLength -= sizeOfDouble;
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler do arquivo: " + e.getMessage());
        }
    }
}
