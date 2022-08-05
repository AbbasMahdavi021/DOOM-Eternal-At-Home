package DoomEternal.functions;


public class Location {

    private float x;
    private float y;

    public Location() {
        this.x = 0;
        this.y = 0;
    }

    public Location(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return this.x;
    }
    public float getY() {
        return this.y;
    }


    public void move(float x, float y) {
        this.x += x;
        this.y += y;
    }


    public void set(Location location) {
        this.x = location.x;
        this.y = location.y;
    }
    public Location add(Location location) {
        float x = this.x + location.x;
        float y = this.y + location.y;
        return new Location(x, y);
    }
}
