package mapgen.tiles;

import java.awt.Color;
import mapgen.TileType;

public class DeepWater extends Tile {

    public DeepWater() {
        this.tileType = TileType.DeepWater;
        this.color = new Color(0, 0, 139);

        TileType[] types = { TileType.DeepWater, TileType.Water };
        this.constraintsNorth = types;
        this.constraintsSouth = types;
        this.constraintsEast = types;
        this.constraintsWest = types;
    }
}
