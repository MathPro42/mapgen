package mapgen.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class MapPreviewPanel extends JPanel {
    private BufferedImage image;
    private double zoom = 1.0;
    private String message = "Click Generate to preview map";

    public void setImage(BufferedImage img) {
        this.image = img;
        this.message = "";
        repaint();
    }

    public void setMessage(String msg) {
        this.message = msg;
        this.image = null;
        repaint();
    }

    public void setZoom(double z) {
        this.zoom = z;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            int w = (int) (image.getWidth() * zoom);
            int h = (int) (image.getHeight() * zoom);
            // Center the image if it is smaller than the panel
            int x = Math.max(0, (getWidth() - w) / 2);
            int y = Math.max(0, (getHeight() - h) / 2);
            g2d.drawImage(image, x, y, w, h, null);
            g2d.dispose();
        } else if (!message.isEmpty()) {
            Graphics2D g2d = (Graphics2D) g.create();
            int stringWidth = g2d.getFontMetrics().stringWidth(message);
            g2d.drawString(message, (getWidth() - stringWidth) / 2, getHeight() / 2);
            g2d.dispose();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (image != null) {
            return new Dimension((int) (image.getWidth() * zoom), (int) (image.getHeight() * zoom));
        }
        return super.getPreferredSize();
    }
}
