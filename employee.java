

public class employee {
    private String name;
    private String vacancy;
    private String email;
    private String number;
    private int salary;
    private int age;

    public employee(String name, String vacancy, String email,
                    String number, int salary, int age) {
        this.name = name;
        this.vacancy = vacancy;
        this.email = email;
        this.number = number;
        this.salary = salary;
        this.age = age;
    }

    public void info() {
        System.out.println("ФИО: " + name);
        System.out.println("Должность: " + vacancy);
        System.out.println("email: " + email);
        System.out.println("Номер: " + number);
        System.out.println("Зарплата: " + salary);
        System.out.println("Возраст: " + age);
    }

    public int getAge() {
        return age;
    }
}