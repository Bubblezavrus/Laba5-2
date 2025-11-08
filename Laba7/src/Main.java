import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int n = 1000;

        System.out.println("=== Надпростi числа до " + n + " ===");

        long count = IntStream.rangeClosed(2, n)
                .filter(Main::isPrime)
                .filter(i -> isPrime(reverseNumber(i)))
                .peek(i -> System.out.print(i + " "))
                .count();

        System.out.println("\n\nКількість надпростих чисел до " + n + ": " + count);
    }

    // Перевірка, чи число просте
    private static boolean isPrime(int num) {
        if (num < 2) return false;
        return IntStream.rangeClosed(2, (int) Math.sqrt(num))
                .noneMatch(i -> num % i == 0);
    }

    // Переворот цифр числа
    private static int reverseNumber(int num) {
        return Integer.parseInt(new StringBuilder(String.valueOf(num)).reverse().toString());
    }
}
