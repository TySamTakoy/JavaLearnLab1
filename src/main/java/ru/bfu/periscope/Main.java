package ru.bfu.periscope;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();

        // Подсчет частоты символов через хэш-мапу <значение, число вхождений>
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            if (c != '\n') {
                frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
            }
        }

        // Сортировка символов по убыванию частоты, затем лексикографически
        List<Map.Entry<Character, Integer>> sortedEntries = new ArrayList<>(frequencyMap.entrySet());
        sortedEntries.sort((a, b) -> {
            int compare = b.getValue().compareTo(a.getValue());
            if (compare == 0) {
                return a.getKey().compareTo(b.getKey());
            }
            return compare;
        });

        // Ограничение до 10 наиболее часто встречающихся символов
        List<Map.Entry<Character, Integer>> topEntries = sortedEntries.subList(0, Math.min(10, sortedEntries.size()));

        // Нахождение максимального количества вхождений для масштабирования гистограммы
        int maxFrequency = topEntries.isEmpty() ? 0 : topEntries.getFirst().getValue();

        // Построение гистограммы
        for (int i = 10; i >= 0; i--) {
            for (Map.Entry<Character, Integer> entry : topEntries) {
                int height = (int) Math.round((double) entry.getValue() / maxFrequency * 10);
                if (height > i) {
                    System.out.print("  #");
                } else if (height == i) {
                    System.out.printf("%3d", entry.getValue());
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }

        // Вывод символов под гистограммой
        for (Map.Entry<Character, Integer> entry : topEntries) {
            System.out.printf("%3c", entry.getKey());
        }
        System.out.println();
    }
}
