

public class Animal {
    private String name;
    private static int count;

    public Animal(String name) {
        this.name = name;
        count++;
    }

    public  void run(int dist) {
        if (dist > 0) {
            System.out.printf("%s пробежал %s м%n", name, dist);
        } else {
            wrong();
        }
    }

    public  void swim(int dist) {
        if (dist > 0) {
            System.out.printf("%s пробежал %s м%n", name, dist);
        } else {
            wrong();
        }
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        System.out.printf("Создано животных: %s%n", count);
        return count;
    }

    public void wrong() {
        System.out.println("Неккоретная длина препятствия");
    }
}
