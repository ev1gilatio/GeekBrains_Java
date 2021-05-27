

public class Main {
    public static void main(String[] args) {

        employee[] emp = new employee[5];

        emp[0] = new employee("Солдатов Виктор Иванович", "Инженер",
                "vicsold@gmail.com", "88005563535", 250, 51);

        emp[1] = new employee("Иванов Петр Иванович", "Программист",
                "petiv@gmail.com", "88015553566", 251, 21);

        emp[2] = new employee("Павлов Иван Викторович", "Повар",
                "ivpavn@gmail.com", "88025553995", 133, 35);

        emp[3] = new employee("Петров Василий Иванович", "Уборщик",
                "vaspet@gmail.com", "88035563531", 57, 47);

        emp[4] = new employee("Иванов Иван Владимирович", "Администратор",
                "iviv@gmail.com", "88045557835", 199, 39);

        for (int i = 0; i < emp.length; i++) {
            if (emp[i].getAge() > 40) {
                emp[i].info();
                System.out.println();
            }
        }
    }
}