package mapgen.tiles;

import java.awt.Color;
import mapgen.TileType;

public class Water extends Tile {

    public Water() {
        this.tileType = TileType.Water;
        this.color = Color.BLUE;

        TileType[] types = {
            TileType.Water,
            TileType.Coast,
            TileType.DeepWater,
        };
        this.constraintsNorth = types;
        this.constraintsSouth = types;
        this.constraintsEast = types;
        this.constraintsWest = types;
    }
}
