public class teste {
  public static void main(String[] args) {
    String[] arr = {"bub", "tic", "tes"};

    String s = "bub";
    boolean teste = false;

    for (int i = 0; i < 3; i ++) {
      if (arr[i].equals(s)) {
        teste = true;
        break;
      }
    }

    if (teste) {
      System.out.println("esta presente");
    } else {
      System.out.println("nao esta presente");
    }

  }
}
