package world;

import main.Global;
import util.OpenSimplexNoise;
import world.entity.Entity;
import world.entity.Player;
import world.tile.*;
import world.tile.Tile_Tree_Oak;

import java.awt.*;
import java.util.Random;

public class World implements java.io.Serializable {

    //public WorldObject[][][] layers;
    private int[][][] layers; //holds worldIDs of each worldObject, 0 meaning empty, 1 meaning player
    //0: Ground Tiles
    //1: Tiles and Entities

    public static final int GROUND_TILES = 0;
    public static final int TILES_ENTITIES = 1;

    private WorldObject[] worldObjects; //each worldID corresponds to the index of a worldObject in this array

    public int size;
    private long seed;

    private int nextIndex=2; //

    public String saveName;


    public World(int size, long seed) {

        this.size = size;
        this.seed = seed;

        //layers = new WorldObject[3][size][size];
        layers = new int[3][size][size];
        worldObjects = new WorldObject[3 * size * size];

        generate();

        /*
        try {
            FileReader in = new FileReader("res/world/tempWorld.txt");
            BufferedReader read = new BufferedReader(in);

            for (int y = 0; y < tiles.length; y++) {
                String[] tileNums = read.readLine().split(" ");

                for (int x = 0; x < tiles[0].length; x++) {
                    int tileNum = Integer.parseInt(tileNums[x]);
                    groundTiles[y][x] = tileNum;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
         */
    }

    public void generate() {
        OpenSimplexNoise noise = new OpenSimplexNoise(seed);
        Random random = new Random(seed);

        Point pos = new Point();

        //GROUND TILES
        for (pos.y = 0; pos.y < size; pos.y++) {
            for (pos.x = 0; pos.x < size; pos.x++) {
                double eval = noise.eval(pos.getX()/10, pos.getY()/10);

                Tile tile;

                if (eval < -0.5) {
                    tile = new Tile_Water();
                } else {
                    tile = new Tile_Grass();
                }

                add(tile, pos, GROUND_TILES);
            }
        }

        //TILES
        for (pos.y = 0; pos.y < size; pos.y++) {
            for (pos.x = 0; pos.x < size; pos.x++) {
                if (get(GROUND_TILES, pos) instanceof Tile_Water) continue;

                double eval = noise.eval(pos.getX()/10, pos.getY()/10);

                Tile tile;

                if (eval > 0.5) {
                    tile = new Tile_Tree_Oak();
                    if (random.nextInt(16) < 1) {
                        tile.setState(1);
                    }
                } else if (random.nextInt(16) < 1) {
                    tile = new Tile_Bush();
                    if (random.nextInt(4) < 1) {
                        tile.setState(1);
                    }
                } else {
                    tile = null;
                }

                if (tile != null) {
                    add(tile, pos, TILES_ENTITIES);
                }
            }
        }

        //ENTITIES
        remove(new Point(size/2, size/2), TILES_ENTITIES);
        add(new Player(), new Point(size/2, size/2), TILES_ENTITIES, 1);

        //System.out.println("#" + get(GROUND_TILES, new Point(0, 0)).getStateID());
        //System.out.println("##" + get(GROUND_TILES, new Point(1, 0)).getStateID());
    }

    public void tick() {
        for (int i = 0; i < nextIndex; i++) {
            if (worldObjects[i] == null) continue;
            if (worldObjects[i] instanceof Entity) {
                ((Entity) worldObjects[i]).tick();
            }
        }
    }

    public void update() {
        for (int i = 0; i < nextIndex; i++) {
            if (worldObjects[i] == null) continue;
            if (worldObjects[i] instanceof Entity) {
                ((Entity) worldObjects[i]).update();
            }
        }
    }

    public void render(Graphics2D g2) {

        Point TL = new Point(); //Top Left
        //Point BR = new Point(); //Bottom Right
        TL.x = Global.game.camera.x - (Global.maxTileX/2);
        TL.y = Global.game.camera.y - (Global.maxTileY/2);
        //BR.x = TL.x + Global.maxTileX;
        //BR.y = TL.y + Global.maxTileY;

        Point tempPos = new Point();

        for (int layer = 0; layer < layers.length; layer++) {
            for (tempPos.y = 0; tempPos.y < Global.maxTileY; tempPos.y++) {
                for (tempPos.x = 0; tempPos.x < Global.maxTileX; tempPos.x++) {
                    if (getWorldID(layer, new Point(tempPos.x + TL.x, tempPos.y + TL.y)) == 0) continue;

                    get(layer, new Point(tempPos.x + TL.x, tempPos.y + TL.y)).render(g2, tempPos);
                }
            }
        }
    }

    public void remove(Point pos, int layer) {
        if (getWorldID(layer, pos) == 0) return;
        worldObjects[getWorldID(layer, pos)] = null;
        setWorldID(layer, pos, 0);
    }

    public void add(WorldObject w, Point pos, int layer, int id) {
        //System.out.println("w: " + w + "  pos: " + pos + "  layer: " + layer + "  int: " + id);
        if (getWorldID(layer, pos) != 0) return;
        w.setWorldID(id);
        w.setPos(pos);
        worldObjects[id] = w;
        setWorldID(layer, pos, id);
    }

    public void add(WorldObject w, Point pos, int layer) {
        add(w, pos, layer, nextIndex);
        nextIndex++;
    }

    //Returns false if newPos is occupied
    public boolean move(Point oldPos, Point newPos) {
        if (!empty(newPos, TILES_ENTITIES)) {
            System.out.println(get(1, newPos));
            return false;
        }

        setWorldID(TILES_ENTITIES, newPos, getWorldID(TILES_ENTITIES, oldPos));
        setWorldID(TILES_ENTITIES, oldPos, 0);

        return true;
    }

    //returns true if empty
    public boolean empty(Point pos, int layer) {
        return getWorldID(layer, pos) == 0;
    }

    public void interact(Point pos, Entity e) {
        WorldObject w;
        for (int i = layers.length-1; i >= 0; i--) {
            if ((w = get(i, pos)) != null) {
                w.interact(e);
                return;
            }
        }
    }

    public WorldObject get(int layer, Point pos) {
        int id = getWorldID(layer, pos);
        if (id == 0) {
            return null;
        } else {
            return worldObjects[id];
        }
    }

    public Point getPos(int layer, int worldID) {
        Point pos = new Point();
        for (pos.y = 0; pos.y < size; pos.y++) {
            for (pos.x = 0; pos.x < size; pos.x++) {
                if (getWorldID(layer, pos) == worldID) {
                    return pos;
                }
            }
        }
        //worldID not found in layers
        return new Point(-1, -1);
    }

    public int getWorldID(int layer, Point pos) {
        return layers[layer][pos.y][pos.x];
    }

    public void setWorldID(int layer, Point pos, int id) {
        layers[layer][pos.y][pos.x] = id;
    }

    public Point getPlayerPos() {
        return worldObjects[1].getPos();
    }
}
