package DoomEternal.functions;

public class Respawn {

    private final LocationChange locationChange;

    public Respawn(float xPosition, float yPosition, float rotation) {
        this.locationChange = new LocationChange(xPosition, yPosition, rotation);
    }

    public LocationChange getTransform() {
        return this.locationChange;
    }
}
