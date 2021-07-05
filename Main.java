

public class Main {
    public static void main(String[] args) {
        Participant[] part = new Participant[3];

        part[0] = new Human("Ivan", 25,1);
        part[1] = new Cat("Barsik", 35, 10);
        part[2] = new Robot("Rob", 1000, 0);

        Obstacle[] obs = new Obstacle[3];

        obs[0] = new Wall(5);
        obs[1] = new Track(21);
        obs[2] = new Wall(4);

        for (int i = 0; i < part.length; i++) {
            for (int j = 0; j < obs.length; j++) {
                part[i].passObs(obs[j]);
            }
        }
    }
}