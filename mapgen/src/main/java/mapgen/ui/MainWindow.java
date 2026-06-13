package mapgen.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

public class MainWindow extends JFrame {

    private ControlPanel controlPanel;
    private MapPreviewPanel mapPreviewPanel;
    private JSlider zoomSlider;
    private JLabel zoomLabel;

    public MainWindow() {
        setTitle("Procedural Map Generator");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        controlPanel = new ControlPanel();
        
        JPanel leftContainer = new JPanel(new BorderLayout());
        leftContainer.setPreferredSize(new Dimension(250, 0));
        leftContainer.add(controlPanel, BorderLayout.NORTH);

        add(leftContainer, BorderLayout.WEST);

        mapPreviewPanel = new MapPreviewPanel();
        JScrollPane scrollPane = new JScrollPane(mapPreviewPanel);
        
        JPanel zoomPanel = new JPanel(new BorderLayout(10, 0));
        zoomPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        zoomPanel.add(new JLabel("Zoom:"), BorderLayout.WEST);
        
        zoomSlider = new JSlider(10, 500, 100);
        zoomSlider.setMajorTickSpacing(100);
        zoomSlider.setMinorTickSpacing(10);
        zoomSlider.setPaintTicks(true);
        zoomSlider.setEnabled(false); 
        
        zoomLabel = new JLabel("100%");
        zoomLabel.setPreferredSize(new Dimension(50, 20));
        zoomLabel.setHorizontalAlignment(JLabel.RIGHT);
        
        zoomSlider.addChangeListener(e -> {
            int zoomPercent = zoomSlider.getValue();
            zoomLabel.setText(zoomPercent + "%");
            mapPreviewPanel.setZoom(zoomPercent / 100.0);
            mapPreviewPanel.revalidate();
            mapPreviewPanel.repaint();
        });
        
        zoomPanel.add(zoomSlider, BorderLayout.CENTER);
        zoomPanel.add(zoomLabel, BorderLayout.EAST);
        
        JPanel centerContainer = new JPanel(new BorderLayout());
        centerContainer.add(scrollPane, BorderLayout.CENTER);
        centerContainer.add(zoomPanel, BorderLayout.SOUTH);
        
        add(centerContainer, BorderLayout.CENTER);
    }

    public ControlPanel getControlPanel() { return controlPanel; }
    public MapPreviewPanel getMapPreviewPanel() { return mapPreviewPanel; }
    
    public void setZoomEnabled(boolean enabled) { zoomSlider.setEnabled(enabled); }
    public int getZoomValue() { return zoomSlider.getValue(); }
}
