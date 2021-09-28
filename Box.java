import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> arr;

    public Box() {
        arr = new ArrayList<>();
    }

    public void addFruit(T fruit) {
        if (arr.isEmpty()) {
            adding(arr, fruit);
        } else {
            if (arr.get(0).getClass() == fruit.getClass()) {
                adding(arr, fruit);
            } else {
                System.out.println("Wrong fruit");
            }
        }
    }

    private void adding(ArrayList<T> arr, T fruit) {
        arr.add(fruit);
        System.out.println("Fruit has been added");
    }

    public ArrayList<T> getContent() {
        return arr;
    }

    public float getWeight() {
        float weight = 0;

        if (!arr.isEmpty()) {
            weight = arr.get(0).getWeight() * arr.size();
        }

        return weight;
    }

    public boolean compare(Box box) {
        return (this.getWeight() == box.getWeight());
    }

    public void move(Box box) {
        ArrayList<T> anotherArr;
        anotherArr = box.getContent();

        if (!arr.isEmpty()) {
            if (!anotherArr.isEmpty()) {
                if (arr.get(0).getClass() == anotherArr.get(0).getClass()) {
                    moving(arr, anotherArr);
                } else {
                    System.out.println("There are another fruits in this box");
                }
            } else {
                moving(arr, anotherArr);
            }
        } else {
            System.out.println("This box is empty");
        }
    }

    private void moving(ArrayList<T> arr1, ArrayList<T> arr2) {
        arr2.addAll(arr1);
        arr1.clear();
        System.out.println("All fruits were moved to another box");
    }
}