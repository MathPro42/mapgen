package mapgen.tiles;

import java.awt.Color;
import mapgen.TileType;

public class Cactus extends Tile {

    public Cactus() {
        this.tileType = TileType.Cactus;
        this.color = new Color(154, 205, 50);

        TileType[] types = { TileType.Desert };
        this.constraintsNorth = types;
        this.constraintsSouth = types;
        this.constraintsEast = types;
        this.constraintsWest = types;
    }
}
