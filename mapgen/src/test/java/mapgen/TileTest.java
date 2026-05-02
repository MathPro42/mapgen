package mapgen;

import static org.junit.jupiter.api.Assertions.*;

import mapgen.tiles.Tile;
import org.junit.jupiter.api.Test;

class TileTest {

    // A minimal anonymous/dummy class to test the abstract base logic
    class DummyTile extends Tile {

        public DummyTile() {
            this.tileType = TileType.Desert;
            this.constraintsNorth = new TileType[] {
                TileType.Desert,
                TileType.Cactus,
            };
        }

        // This is how you are expected to implement this in the base Tile class!
        @Override
        public boolean isTileInConstraint(TileType[] constraints) {
            for (TileType type : constraints) {
                if (type == this.tileType) return true;
            }
            return false;
        }

        @Override
        public TileType[] getConstraints(Orientation orientation) {
            return orientation == Orientation.North
                ? constraintsNorth
                : new TileType[0];
        }
    }

    @Test
    void testIsTileInConstraint() {
        DummyTile tile = new DummyTile();

        TileType[] validConstraints = { TileType.Water, TileType.Desert };
        TileType[] invalidConstraints = { TileType.Water, TileType.Forest };

        assertTrue(
            tile.isTileInConstraint(validConstraints),
            "Tile should recognize it is allowed in this list."
        );
        assertFalse(
            tile.isTileInConstraint(invalidConstraints),
            "Tile should recognize it is NOT allowed in this list."
        );
    }

    @Test
    void testGetConstraintsByOrientation() {
        DummyTile tile = new DummyTile();

        TileType[] northConstraints = tile.getConstraints(Orientation.North);
        assertEquals(
            2,
            northConstraints.length,
            "Should return the 2 constraints defined for North."
        );
        assertEquals(
            TileType.Cactus,
            northConstraints[1],
            "Second constraint should be Cactus."
        );
    }
}
