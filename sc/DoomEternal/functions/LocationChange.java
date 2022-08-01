package DoomEternal.functions;


public class LocationChange {

    private final Location location;
    private float angle;


    public LocationChange() {
        this.location = new Location();
        this.angle = 0f;
    }
    public LocationChange(float x, float y, float angle) {
        this.location = new Location(x, y);
        this.angle = angle;
    }


    public Location getPosition() {
        return this.location;
    }
    public float getX() {
        return this.location.getX();
    }
    public float getY() {
        return this.location.getY();
    }
    public float getAngle() {
        return this.angle;
    }
    public void setPosition(Location location) {
        this.location.set(location);
    }
    public void setAngle(float angle) {
        this.angle = angle;
    }



    public void moveTank(float xy) {
        float mx = (float) (xy * Math.cos(Math.toRadians(this.angle)));
        float my = (float) (xy * Math.sin(Math.toRadians(this.angle)));
        this.location.move(mx, my);
    }
    public void moveObj(float x, float y) {
        this.location.move(x, y);
    }
    public void rotate(float r) {
        if (this.angle >= 360.0f) {
            this.angle -= 360.0f;
        } else if (this.angle <= 0.0f) {
            this.angle += 360.0f;
        }
        this.angle += r;
    }
}
