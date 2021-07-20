import java.util.*;

public class Main {
    public static void main(String[] args) {
        uniqueWords(createArr());
        createPhoneBook();
    }

    private static String[] createArr() {
        String[] arr = new String[] {
                "Apple", "Banana", "Pineapple", "Orange", "Orange", "Apple", "Plane",
                "Blue", "Black", "Red", "Apple", "Blue", "Orange", "Car", "Plane"
        };

        for (String s : arr) {
            System.out.print(s + " ");
        }
        System.out.println();

        return arr;
    }

    private static void uniqueWords(String[] arr) {
        Set<String> set = new LinkedHashSet<>();

        for (String word : arr) {
            set.add(word);
        }

        System.out.println(set);

        uniqueWordsCount(set, arr);
    }

    private static void uniqueWordsCount(Set<String> set, String[] arr) {
        Iterator<String> it = set.iterator();
        Map<String, Integer> map = new LinkedHashMap<>();

        while(it.hasNext()) {
            map.put(it.next(), 0);
        }

        for (String s : arr) {
            if (map.containsKey(s)) {
                int key = map.get(s);
                key++;
                map.put(s, key);
            }
        }

        System.out.println(map);
    }

    private static void createPhoneBook() {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Ivanov", "+7(917)998-75-85");
        phoneBook.add("Ivanov", "+7(963)115-85-98");
        phoneBook.add("Petrov", "+7(999)665-75-45");

        System.out.println(phoneBook.get("Ivanov"));
        System.out.println(phoneBook.get("Petrov"));
    }
}