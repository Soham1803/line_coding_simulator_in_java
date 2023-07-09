import javax.swing.*;
import java.awt.*;

public class GraphFrame extends JFrame {
    public GraphFrame() {
        setTitle("Graph Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create a custom component for drawing
        GraphComponent graphComponent = new GraphComponent();

        // Add the custom component to the frame
        add(graphComponent);

        setVisible(true);
    }

    // Custom component for drawing
    private static class GraphComponent extends JComponent {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int width = getWidth();
            int height = getHeight();

            // Set color and thickness for axes
            g.setColor(Color.BLACK);
            //g.setStroke(new BasicStroke(2));

            // Draw x-axis
            g.drawLine(0, height / 2, width, height / 2);

            // Draw y-axis
            g.drawLine(width / 2, 0, width / 2, height);

            // Draw polyline
            int[] xPoints = {50, 100, 150, 200, 250};
            int[] yPoints = {height / 2 - 50, height / 2 - 100, height / 2 + 50, height / 2 - 50, height / 2 + 100};
            int numPoints = xPoints.length;
            g.setColor(Color.RED);
            g.drawPolyline(xPoints, yPoints, numPoints);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GraphFrame();
            }
        });
    }
}