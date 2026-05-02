package mapgen.tiles;

import java.awt.Color;
import mapgen.TileType;

public class Land extends Tile {

    public Land() {
        this.tileType = TileType.Land;
        this.color = Color.GREEN;

        TileType[] types = {
            TileType.Land,
            TileType.Desert,
            TileType.Mountain,
            TileType.Forest,
            TileType.Tree,
            TileType.Coast,
        };
        this.constraintsNorth = types;
        this.constraintsSouth = types;
        this.constraintsEast = types;
        this.constraintsWest = types;
    }
}
