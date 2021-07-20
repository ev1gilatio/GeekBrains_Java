import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PhoneBook {
    private final Map<String, Set<String>> map = new HashMap<>();

    public void add(String name, String number) {
        if (map.containsKey(name)) {
            map.get(name).add(number);
        } else {
            Set<String> numbers = new HashSet<>();
            numbers.add(number);
            map.put(name, numbers);
        }
    }

    public Set<String> get(String name) {
        return map.get(name);
    }
}