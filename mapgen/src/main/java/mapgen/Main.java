package mapgen;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import mapgen.ui.MainWindow;
import mapgen.ui.MapController;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow();
            new MapController(window);
            window.setVisible(true);
        });
    }
}
