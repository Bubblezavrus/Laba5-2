import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//https://example.com/

public class Main {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Аналіз сторінки ===");
        System.out.println("Введіть URL: ");
        String url = scanner.nextLine();

        try {
            Scanner urlScanner = new Scanner(new URL(url).openStream());
            StringBuilder html = new StringBuilder();
            while (urlScanner.hasNext()) {
                html.append(urlScanner.nextLine());
            }
            urlScanner.close();

            Pattern pattern = Pattern.compile("<\\s*([a-zA-Z0-9]+)(\\s|>)");
            Matcher matcher = pattern.matcher(html.toString());

            Map<String, Integer> tagCount = new HashMap<>();

            System.out.println("\n=== Попередній перегляд HTML ===\n");
            System.out.println(html.substring(0, Math.min(html.length(), 500)));

            while (matcher.find()) {
                String tag = matcher.group(1).toLowerCase();
                tagCount.put(tag, tagCount.getOrDefault(tag, 0) + 1);
            }
            System.out.println("\n=== Теги за алфавітом ===");
            tagCount.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));

            System.out.println("\n=== Теги за частотою ===");
            tagCount.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
        } catch (IOException e) {
            System.out.println("Помилка при зчитуванні сторінки: " + e.getMessage());

        }
    }
}