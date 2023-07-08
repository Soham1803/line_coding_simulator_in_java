package Assignments;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

class SecondFrame extends JFrame {

    private JLabel label;
    private JPanel panel;
    public SecondFrame(String input) {
        setTitle("Second Frame");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(243, 170, 96));

        GraphComponent graphLayout = new GraphComponent(input, "");
        panel.add(graphLayout);

        add(panel);
        /*label = new JLabel(input);


        add(label);*/
        setVisible(true);
    }

    private static class GraphComponent extends JComponent {

        private String signalString;
        private String modulation;

        public GraphComponent(String input, String modulation){
            this.signalString = input;
            this.modulation = modulation;


        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int width = getWidth();
            int height = getHeight();

            g.setColor(Color.BLACK);

            g.drawLine(0, height-50, width, height-50);

            g.drawLine(50, 0, 50, height);
            g.setColor(Color.cyan);

            int signalStart = 50;

            int nPoints = signalString.length();
            int[] x_array = new int[nPoints*2 + 2];
            int[] y_array = new int[nPoints*2 + 1];

            int arrIndex = 0;
            x_array[0] = 50;

            for(int i = 0; i < nPoints; i+=1){
                if(signalString.charAt(i) == '1'){
                    x_array[arrIndex+1] = signalStart+50;
                    y_array[arrIndex] = height-300;
                    arrIndex++;
                    x_array[arrIndex+1] = signalStart+50;
                    y_array[arrIndex] = height-300;
                    arrIndex++;

                }else if(signalString.charAt(i) == '0'){
                    x_array[arrIndex+1] = signalStart+50;
                    y_array[arrIndex] = height-50;
                    arrIndex++;
                    x_array[arrIndex+1] = signalStart+50;
                    y_array[arrIndex] = height-50;
                    arrIndex++;

                }
                signalStart+=50;
                g.drawPolyline(x_array, y_array, nPoints);
            }

            System.out.println(Arrays.toString(x_array));
            System.out.println(Arrays.toString(y_array));

            g.setColor(Color.BLACK);
            g.setFont(new Font("Osaka", Font.PLAIN, 12));
            g.drawString("0", 40, height-35);

            float x_pos = 100;
            while(x_pos < width - 50){
                float x_coordinate = (x_pos - 50)/100;
                g.drawString(""+x_coordinate, (int)x_pos, height-35);
                x_pos += 50;
            }

            float y_pos = height - 100;
            while(y_pos > 50){
                float y_coordinate = (height - y_pos - 50)/50;
                g.drawString(""+y_coordinate, 25, (int)y_pos);
                y_pos -= 50;
            }

            g.setColor(Color.RED);
            g.setFont(new Font("Osaka", 3, 20));
            g.drawString("X", width - 50, height-30);

            g.setColor(Color.BLUE);
            g.setFont(new Font("Osaka", 3, 20));
            g.drawString("Y" , 30, 30);
        }
    }
}

public class Assignment_1 extends JFrame{

    private JLabel label;
    private JTextField textField;
    private JComboBox<String> comboBox;
    private JButton button;

    public Assignment_1() {

        setTitle("Digital Sequence GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setBackground(new Color(70, 139, 151));

        label = new JLabel("Digital Sequence");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 0, 0); // Add top margin
        centerPanel.add(label, gbc);

        textField = new JTextField(25);
        textField = new JTextField(25);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 0, 0);
        centerPanel.add(textField, gbc);

        String[] options = {"Uni-polar NRZ", "Bi-Polar NRZ-L", "Bi-Polar NRZ-I", "Polar Z", "Bi-Polar AMI",
                "Pseudo-Ternary", "Manchester", "Differentail Manchester", "MLT-3"};
        comboBox = new JComboBox<>(options);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 0, 0); // Add top margin
        centerPanel.add(comboBox, gbc);

        add(centerPanel, BorderLayout.CENTER);

        button = new JButton("Draw Graph");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 0, 0, 0);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String input = textField.getText();

                dispose();
                SecondFrame secondFrame = new SecondFrame(input);
            }
        });
        centerPanel.add(button, gbc);

        add(new JPanel(), BorderLayout.NORTH);
        add(new JPanel(), BorderLayout.WEST);
        add(new JPanel(), BorderLayout.EAST);

        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Assignment_1();
            }
        });
    }

}
