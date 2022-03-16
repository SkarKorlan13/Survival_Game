package world;

import util.OpenSimplexNoise;
import world.entity.Entity;
import world.entity.Player;
import world.tile_entity.Bush;
import world.tile_entity.Tile_Entity;
import world.tile.*;
import world.tile_entity.Tree_Oak;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class World implements java.io.Serializable {

    private Tile[][] tiles; //holds tiles

    private Tile_Entity[][] tile_entities; //holds tile_entities

    //other layers?

    private ArrayList<Entity> entities; //each worldID corresponds to the index of an Entity in this array

    private int size;
    private long seed;

    private int nextIndex=2;

    private String saveName;

    public World(int size, long seed) {
        this.size = size;
        this.seed = seed;

        tiles = new Tile[size][size];
        tile_entities = new Tile_Entity[size][size];
        entities = new ArrayList<>();

        generate();
    }

    public void generate() {
        OpenSimplexNoise noise = new OpenSimplexNoise(seed);
        Random random = new Random(seed);

        Point pos = new Point();

        //TILES
        for (pos.y = 0; pos.y < size; pos.y++) {
            for (pos.x = 0; pos.x < size; pos.x++) {
                double eval = noise.eval(pos.getX()/10, pos.getY()/10);

                Tile tile;

                if (eval < -0.5) {
                    tile = new Tile_Water();
                } else {
                    tile = new Tile_Grass();
                }

                addTile(tile, pos);
            }
        }

        //TILE_ENTITIES
        for (pos.y = 0; pos.y < size; pos.y++) {
            for (pos.x = 0; pos.x < size; pos.x++) {
                if (getTile(pos) instanceof Tile_Water) continue;

                double eval = noise.eval(pos.getX()/10, pos.getY()/10);

                Tile_Entity tile_entity;

                if (eval > 0.5) {
                    tile_entity = new Tree_Oak();
                    if (random.nextInt(16) < 1) {
                        tile_entity.setState(1);
                    }
                } else if (random.nextInt(16) < 1) {
                    tile_entity = new Bush();
                    if (random.nextInt(4) < 1) {
                        tile_entity.setState(1);
                    }
                } else {
                    tile_entity = null;
                }

                if (tile_entity != null) {
                    addTile_Entity(tile_entity, pos);
                }
            }
        }

        //ENTITIES
        addEntity(new Player(new Point(0, 0)), new Point(size/2, size/2), 1);
    }

    //TODO all of this

    public void addEntity(Entity entity, Point pos) {
        addEntity(entity, pos, nextIndex);
        nextIndex++;
    }

    public void addEntity(Entity entity, Point pos, int id) {
        if (entities.get(id) != null) {
            System.out.println("Entity overridden at " + pos + ": " + entity);
        }
        entities.set(id, entity);
    }

    public void addTile_Entity(Tile_Entity tile_entity, Point pos) {
        tile_entities[pos.y][pos.x] = tile_entity;
    }

    public void addTile(Tile tile, Point pos) {
        tiles[pos.y][pos.x] = tile;
    }

    public Tile getTile(Point pos) {
        return tiles[pos.y][pos.x];
    }



    /*
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
        TL.x = Global.game.camera.x - (Global.maxTileX/2);
        TL.y = Global.game.camera.y - (Global.maxTileY/2);

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

    public void add(Entity w, Point pos, int id) {
        //System.out.println("w: " + w + "  pos: " + pos + "  layer: " + layer + "  int: " + id);
        if (getWorldID(layer, pos) != 0) {
            remove(pos, layer);
        }

        if (!passable(pos)) {
            remove(pos, TILES);
        }

        w.setWorldID(id);
        w.setPos(pos);
        worldObjects[id] = w;
        setWorldID(layer, pos, id);
    }

    public void add(Entity w, Point pos) {
        add(w, pos, layer, nextIndex);
        nextIndex++;
    }

    public boolean passable(Point pos) {
        if (getWorldID(TILES, pos) == 0) {
            return true;
        } else {
            return ((Tile) get(TILES, pos)).isPassable();
        }
    }

    //Returns false if newPos is not passable
    public boolean move(Point oldPos, Point newPos) {
        if (!(passable(newPos) && empty(newPos, ENTITIES))) {
            System.out.println(get(1, newPos));
            return false;
        }

        setWorldID(ENTITIES, newPos, getWorldID(ENTITIES, oldPos));
        setWorldID(ENTITIES, oldPos, 0);

        return true;
    }

    //returns true if empty
    public boolean empty(Point pos, int layer) {
        return getWorldID(layer, pos) == 0;
    }

    public void interact(Point pos, Entity e) {
        for (int i = layers.length-1; i >= 0; i--) {
            if (getWorldID(i, pos) != 0) {
                get(i, pos).interact(e);
                return;
            }
        }
    }

    public Entity get(Point pos) {
        int id = getWorldID(layer, pos);
        if (id == 0) {
            return null;
        } else {
            return worldObjects[id];
        }
    }

    //probably unnecessary
    public Point getPos(int worldID) {
        return get()

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
        return entities.get(1).getPos();
    }

    public Player getPlayer() {
        return (Player) entities.get(1);
    }
    */

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public int getSize() {
        return size;
    }
}
