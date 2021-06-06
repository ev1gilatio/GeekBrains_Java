

public class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    @Override
    public void run(int dist) {
        if (dist > 200) {
            System.out.printf("%s не справится с поставленной задачей%n", getName());
        } else if (dist > 0 && dist <= 200) {
            System.out.printf("%s пробежал %s м%n", getName(), dist);
        } else {
            wrong();
        }
    }

    @Override
    public void swim(int dist) {
        if (dist > 0) {
            System.out.printf("Коты боятся воды, и %s в том числе%n", getName());
        } else {
            wrong();
        }
    }
}