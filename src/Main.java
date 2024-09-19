import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class SpiralPanel extends JPanel {
    private int width;
    private int gap;

    // Set the parameters for drawing the spiral
    public void setParameters(int width, int gap) {
        this.width = width;
        this.gap = gap;
        repaint();  // Repaint the panel with the new parameters
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Call superclass method for proper painting

        String hexColor = "0x45e5B";
        g.setColor(Color.decode(hexColor));

        // Draw the spiral based on width and gap
        for (int i = 0; i < 10000; i++) {
            if ((width - (i * gap)) - (gap * (i + 1) + 10) > gap) {
                g.drawLine(10 + ((i - 1) * gap), 10 + (i * gap), width - (i * gap), 10 + (i * gap));
                g.drawLine(width - (i * gap), 10 + (i * gap), width - (i * gap), width - (i * gap));
                g.drawLine(width - (i * gap), width - (i * gap), 10 + (i * gap), width - (i * gap));
                g.drawLine(10 + (i * gap), width - (i * gap), 10 + (i * gap), gap * (i + 1) + 10);
            } else {
                break;
            }
        }
    }
}

class Spiral extends JFrame {
    private SpiralPanel spiralPanel;
    private JTextField widthField;
    private JTextField gapField;

    public Spiral() {
        // Initialize the frame
        setTitle("Spiral Drawer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLayout(new BorderLayout());

        // Initialize the SpiralPanel
        spiralPanel = new SpiralPanel();
        spiralPanel.setPreferredSize(new Dimension(700, 700));

        // Initialize the input fields and button
        widthField = new JTextField(10);
        gapField = new JTextField(10);
        JButton saveButton = new JButton("Draw Spiral");

        // Add action listener to the button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Parse the input values and update the spiral parameters
                    int width = Integer.parseInt(widthField.getText());
                    int gap = Integer.parseInt(gapField.getText());
                    spiralPanel.setParameters(width, gap);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter valid integers.");
                }
            }
        });

        // Create a panel for the input and button
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Width:"));
        inputPanel.add(widthField);
        inputPanel.add(new JLabel("Gap:"));
        inputPanel.add(gapField);
        inputPanel.add(saveButton);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(spiralPanel, BorderLayout.CENTER);

        // Display the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create and show the Spiral frame
        new Spiral();
    }
}