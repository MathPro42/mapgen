package mapgen.ui;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import mapgen.ClassicPerlinNoise;
import mapgen.INoiseAlgorithm;
import mapgen.MyRandom;
import mapgen.NoiseMapGenerator;
import mapgen.SimplexNoise;

public class MapController {

    private final MainWindow window;
    private final ControlPanel controls;
    private final MapPreviewPanel preview;

    public MapController(MainWindow window) {
        this.window = window;
        this.controls = window.getControlPanel();
        this.preview = window.getMapPreviewPanel();

        this.controls.addGenerateListener(e -> generateMap());
        this.controls.addSaveListener(e -> saveMap());
    }

    private void generateMap() {
        int width = controls.getMapWidth();
        int height = controls.getMapHeight();
        float scale = controls.getNoiseScale();
        int seed;
        try {
            seed = Integer.parseInt(controls.getSeedText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(window, "Invalid Seed. Must be an integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String algorithmName = controls.getAlgorithm();

        controls.setGenerateEnabled(false);
        controls.setSaveEnabled(false);
        window.setZoomEnabled(false);
        preview.setImage(null);
        preview.setMessage("Generating...");

        SwingWorker<BufferedImage, Void> worker = new SwingWorker<>() {
            @Override
            protected BufferedImage doInBackground() throws Exception {
                MyRandom rand = new MyRandom(seed);
                INoiseAlgorithm noiseAlgorithm;
                if ("ClassicPerlin".equalsIgnoreCase(algorithmName)) {
                    noiseAlgorithm = new ClassicPerlinNoise(seed);
                } else {
                    noiseAlgorithm = new SimplexNoise(seed);
                }

                float[][] noiseMap = NoiseMapGenerator.generateNoiseMap(
                    width, height, scale, noiseAlgorithm, rand
                );

                return NoiseMapGenerator.mapToImage(noiseMap, width, height, true);
            }

            @Override
            protected void done() {
                try {
                    BufferedImage generatedMap = get();
                    preview.setImage(generatedMap);
                    controls.setSaveEnabled(true);
                    window.setZoomEnabled(true);
                    preview.setZoom(window.getZoomValue() / 100.0);
                    preview.revalidate();
                    preview.repaint();
                } catch (Exception ex) {
                    preview.setMessage("Error generating map");
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(window, "Error generating map: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    controls.setGenerateEnabled(true);
                }
            }
        };
        worker.execute();
    }

    private void saveMap() {
        BufferedImage currentMap = preview.getImage();
        if (currentMap == null) return;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Map");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
        
        String alg = controls.getAlgorithm().toLowerCase();
        int w = controls.getMapWidth();
        int h = controls.getMapHeight();
        fileChooser.setSelectedFile(new File(alg + "_map_" + w + "x" + h + ".png"));

        int userSelection = fileChooser.showSaveDialog(window);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getName().toLowerCase().endsWith(".png")) {
                fileToSave = new File(fileToSave.getParentFile(), fileToSave.getName() + ".png");
            }
            try {
                ImageIO.write(currentMap, "png", fileToSave);
                JOptionPane.showMessageDialog(window, "Map saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(window, "Error saving map: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
