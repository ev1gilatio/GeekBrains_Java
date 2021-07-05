

public class Wall implements Obstacle {
    private final int height;

    public Wall(int height) {
        this.height = height;
    }

    public String getObs() {
        return "wall";
    }

    public int getInt() {
        return height;
    }
}