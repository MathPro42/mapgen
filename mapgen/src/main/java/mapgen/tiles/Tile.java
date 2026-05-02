package mapgen.tiles;

import java.awt.Color;
import mapgen.Orientation;
import mapgen.TileType;

public abstract class Tile {

    public TileType tileType;
    public TileType[] constraintsEast;
    public TileType[] constraintsWest;
    public TileType[] constraintsNorth;
    public TileType[] constraintsSouth;
    public Color color;

    public TileType[] getConstraints(Orientation orientation) {
        if (orientation == Orientation.East) {
            return constraintsEast;
        } else if (orientation == Orientation.North) {
            return constraintsNorth;
        } else if (orientation == Orientation.West) {
            return constraintsWest;
        } else {
            return constraintsSouth;
        }
    }

    public boolean isTileInConstraint(TileType[] constraints) {
        int i = 0;
        while (i < constraints.length && tileType == constraints[i]) {
            i++;
        }
        return i < constraints.length;
    }
}
