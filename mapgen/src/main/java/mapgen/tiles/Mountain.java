package mapgen.tiles;

import java.awt.Color;
import mapgen.TileType;

public class Mountain extends Tile {

    public Mountain() {
        this.tileType = TileType.Mountain;
        this.color = Color.GRAY;

        TileType[] types = { TileType.Mountain, TileType.Land };
        this.constraintsNorth = types;
        this.constraintsSouth = types;
        this.constraintsEast = types;
        this.constraintsWest = types;
    }
}
