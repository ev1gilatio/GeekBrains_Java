

public class Track implements Obstacle {
    private final int dist;

    public Track(int dist) {
        this.dist = dist;
    }

    public String getObs() {
        return "track";
    }

    public int getInt() {
        return dist;
    }
}