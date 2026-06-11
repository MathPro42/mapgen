package mapgen;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class NoiseMapGenerator {

    public static double getFractalNoise(
        INoiseAlgorithm noiseAlgorithm,
        float x,
        float y,
        float persistence,
        int octaves
    ) {
        float total = 0;
        float frequency = 1.0f;
        float amplitude = 1.0f;
        float maxValue = 0;

        for (int i = 0; i < octaves; i++) {
            total += noiseAlgorithm.getNoise(x * frequency, y * frequency) * amplitude;
            maxValue += amplitude;
            amplitude *= persistence;
            frequency *= 2;
        }
        return total / maxValue;
    }

    public static float[][] generateNoiseMap(
        int mapWidth,
        int mapHeight,
        float noiseScale,
        INoiseAlgorithm noiseAlgorithm,
        MyRandom random
    ) {
        float[][] noiseMap = new float[mapHeight][mapWidth];

        float offsetX = random.next(-100000, 100000);
        float offsetY = random.next(-100000, 100000);

        if (noiseScale <= 0) {
            noiseScale = 0.0001f;
        }

        float maxNoiseHeight = Float.MIN_VALUE;
        float minNoiseHeight = Float.MAX_VALUE;

        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                float sampleX = x / noiseScale + offsetX;
                float sampleY = y / noiseScale + offsetY;

                float noiseValue = (float) getFractalNoise(
                    noiseAlgorithm,
                    sampleX,
                    sampleY,
                    0.5f,
                    6
                );
                noiseMap[y][x] = noiseValue;

                if (noiseValue > maxNoiseHeight) maxNoiseHeight = noiseValue;
                if (noiseValue < minNoiseHeight) minNoiseHeight = noiseValue;
            }
        }

        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                noiseMap[y][x] =
                    (noiseMap[y][x] - minNoiseHeight) /
                    (maxNoiseHeight - minNoiseHeight);
            }
        }

        return noiseMap;
    }

    public static Color getColorFromNoiseValue(float v) {
        if (v < 0.35f) return new Color(105, 142, 187); // Deep Ocean
        if (v < 0.45f) return new Color(133, 169, 203); // Shallow Ocean
        if (v < 0.50f) return new Color(135, 196, 171); // Coastlines
        if (v < 0.60f) return new Color(170, 219, 162); // Lowlands
        if (v < 0.70f) return new Color(245, 238, 155); // Midlands
        if (v < 0.80f) return new Color(246, 182, 101); // Foothills
        if (v < 0.90f) return new Color(216, 92, 59); // Mountains
        return new Color(150, 16, 32); // Peaks
    }

    public static BufferedImage mapToImage(
        float[][] noiseMap,
        int width,
        int height,
        boolean color
    ) {
        BufferedImage image = new BufferedImage(
            width,
            height,
            BufferedImage.TYPE_INT_ARGB
        );
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (color) {
                    image.setRGB(
                        x,
                        y,
                        getColorFromNoiseValue(noiseMap[y][x]).getRGB()
                    );
                } else {
                    int val = (int) (noiseMap[y][x] * 255);
                    val = Math.max(0, Math.min(255, val));
                    Color gray = new Color(val, val, val);
                    image.setRGB(x, y, gray.getRGB());
                }
            }
        }
        return image;
    }
}
