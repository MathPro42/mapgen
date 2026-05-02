package mapgen.tiles;

import java.awt.Color;
import mapgen.TileType;

public class Tree extends Tile {

    public Tree() {
        this.tileType = TileType.Tree;
        this.color = new Color(34, 139, 34);

        TileType[] types = { TileType.Land };
        this.constraintsNorth = types;
        this.constraintsSouth = types;
        this.constraintsEast = types;
        this.constraintsWest = types;
    }
}
