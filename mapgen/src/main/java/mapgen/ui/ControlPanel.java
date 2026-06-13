package mapgen.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class ControlPanel extends JPanel {

    private JComboBox<String> algorithmComboBox;
    private JSpinner widthSpinner;
    private JSpinner heightSpinner;
    private JSpinner scaleSpinner;
    private JTextField seedField;
    private JButton generateButton;
    private JButton saveButton;

    public ControlPanel() {
        setLayout(new GridLayout(0, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(new JLabel("Algorithm:"));
        algorithmComboBox = new JComboBox<>(new String[]{"Simplex", "ClassicPerlin"});
        add(algorithmComboBox);

        add(new JLabel("Width:"));
        widthSpinner = new JSpinner(new SpinnerNumberModel(800, 100, 10000, 100));
        add(widthSpinner);

        add(new JLabel("Height:"));
        heightSpinner = new JSpinner(new SpinnerNumberModel(800, 100, 10000, 100));
        add(heightSpinner);

        add(new JLabel("Noise Scale:"));
        scaleSpinner = new JSpinner(new SpinnerNumberModel(480.0, 1.0, 5000.0, 10.0));
        add(scaleSpinner);

        add(new JLabel("Seed:"));
        JPanel seedPanel = new JPanel(new BorderLayout(5, 0));
        seedField = new JTextField("223");
        JButton randomSeedButton = new JButton("RND");
        randomSeedButton.addActionListener(e -> seedField.setText(String.valueOf((int)(Math.random() * 100000))));
        seedPanel.add(seedField, BorderLayout.CENTER);
        seedPanel.add(randomSeedButton, BorderLayout.EAST);
        add(seedPanel);

        add(new JLabel("")); // Spacer

        generateButton = new JButton("Generate Map");
        add(generateButton);

        saveButton = new JButton("Save Map");
        saveButton.setEnabled(false);
        add(saveButton);
    }

    // Getters
    public String getAlgorithm() { return (String) algorithmComboBox.getSelectedItem(); }
    public int getMapWidth() { return (Integer) widthSpinner.getValue(); }
    public int getMapHeight() { return (Integer) heightSpinner.getValue(); }
    public float getNoiseScale() { return ((Double) scaleSpinner.getValue()).floatValue(); }
    public String getSeedText() { return seedField.getText(); }

    // Stetter
    public void setGenerateEnabled(boolean enabled) { generateButton.setEnabled(enabled); }
    public void setSaveEnabled(boolean enabled) { saveButton.setEnabled(enabled); }

    // Action listeners
    public void addGenerateListener(ActionListener listener) { generateButton.addActionListener(listener); }
    public void addSaveListener(ActionListener listener) { saveButton.addActionListener(listener); }
}
