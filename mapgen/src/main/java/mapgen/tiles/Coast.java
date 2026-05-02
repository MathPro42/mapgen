package mapgen.tiles;

import java.awt.Color;
import mapgen.TileType;

public class Coast extends Tile {

    public Coast() {
        this.tileType = TileType.Coast;
        this.color = new Color(250, 250, 210);

        TileType[] types = { TileType.Water, TileType.Land, TileType.Coast };
        this.constraintsNorth = types;
        this.constraintsSouth = types;
        this.constraintsEast = types;
        this.constraintsWest = types;
    }
}
