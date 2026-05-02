package mapgen.tiles;

import java.awt.Color;
import mapgen.TileType;

public class Forest extends Tile {

    public Forest() {
        this.tileType = TileType.Forest;
        this.color = new Color(0, 100, 0);

        TileType[] types = { TileType.Land, TileType.Forest };
        this.constraintsNorth = types;
        this.constraintsSouth = types;
        this.constraintsEast = types;
        this.constraintsWest = types;
    }
}
