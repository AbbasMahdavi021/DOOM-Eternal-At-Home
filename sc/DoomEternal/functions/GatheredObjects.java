package DoomEternal.functions;

import DoomEternal.game.GameObject;

import java.util.ArrayList;
public class GatheredObjects {

    private static ArrayList<GameObject> gameObjects;
    public static ArrayList<Respawn> respawnPoints;

    public static void initGameObjects() {
        gameObjects = new ArrayList<>();
        respawnPoints = new ArrayList<>();
    }
    public static void spawn(GameObject obj) {
        gameObjects.add(obj);
    }
    public static void destroy(GameObject obj) {
        gameObjects.remove(obj);
    }
    public static int objCount() {
        return gameObjects.size();
    }
    public static GameObject getGameObject(int index) {
        return gameObjects.get(index);
    }

}
