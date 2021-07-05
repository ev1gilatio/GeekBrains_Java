

public class Cat implements Participant {
    private final String name;
    private final int run_dist;
    private final int jump_height;
    private boolean onDistance = true;

    public Cat(String name, int run_dist, int jump_height) {
        this.name = name;
        this.run_dist = run_dist;
        this.jump_height = jump_height;
    }

    public void move() {
        System.out.print(name + " is moving... ");
    }

    public void jump() {
        System.out.print(name + " is jumping... ");
    }

    public void passObs(Obstacle obs) {
        if (onDistance) {
            if (obs.getObs().equals("wall")) {
                jump();
                passing("wall", obs.getInt(), jump_height);
            } else {
                move();
                passing("track", obs.getInt(), run_dist);
            }
        }
    }

    private void passing(String obs, int a, int b) {
        if (a <= b) {
            System.out.printf("%s has passed the %s%n", name, obs);
        } else {
            System.out.printf("%s hasn't passed the %s%n", name, obs);
            onDistance = false;
        }
    }
}