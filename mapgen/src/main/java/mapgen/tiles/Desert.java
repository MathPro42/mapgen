package mapgen.tiles;

import java.awt.Color;
import mapgen.TileType;

public class Desert extends Tile {

    public Desert() {
        this.tileType = TileType.Desert;
        this.color = Color.YELLOW;

        TileType[] types = { TileType.Desert, TileType.Land, TileType.Cactus };
        this.constraintsNorth = types;
        this.constraintsSouth = types;
        this.constraintsEast = types;
        this.constraintsWest = types;
    }
}
