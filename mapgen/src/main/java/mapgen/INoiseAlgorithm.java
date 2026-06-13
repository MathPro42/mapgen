package mapgen;

public interface INoiseAlgorithm {
    /**
     * Generates a 2D noise value.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @return A noise value typically in the range [-1.0, 1.0].
     */
    double getNoise(double x, double y);
}
