package mapgen;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) {
        int width = 5000;
        int height = 5000;
        int seed = 223;
        float scale = 480.0f;
        String algorithmName = "Simplex"; // Can be "ClassicPerlin" or "Simplex"

        System.out.println("==========================================");
        System.out.println("Starting Map Generation using " + algorithmName + " Noise");
        System.out.println("Dimensions: " + width + "x" + height);
        System.out.println("Seed: " + seed);
        System.out.println("==========================================");

        long startTime = System.currentTimeMillis();

        try {
            MyRandom rand = new MyRandom(seed);
            
            INoiseAlgorithm noiseAlgorithm;
            if ("ClassicPerlin".equalsIgnoreCase(algorithmName)) {
                noiseAlgorithm = new ClassicPerlinNoise(seed);
            } else {
                noiseAlgorithm = new SimplexNoise(seed);
            }

            float[][] noiseMap = NoiseMapGenerator.generateNoiseMap(
                width,
                height,
                scale,
                noiseAlgorithm,
                rand
            );

            BufferedImage mapImage = NoiseMapGenerator.mapToImage(
                noiseMap,
                width,
                height,
                true
            );

            String filename = algorithmName.toLowerCase() + "_map_" + width + "x" + height + ".png";
            File outputFile = new File(filename);
            ImageIO.write(mapImage, "png", outputFile);

            long endTime = System.currentTimeMillis();
            long duration = (endTime - startTime);

            System.out.println(
                "Image saved to: " + outputFile.getAbsolutePath()
            );
            System.out.println(
                "Total Generation Time: " + duration + " milliseconds."
            );
        } catch (Exception e) {
            System.err.println("\n[ERROR] An unexpected error occurred:");
            e.printStackTrace();
        }
    }
}
