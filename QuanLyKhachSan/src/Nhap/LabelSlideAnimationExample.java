package Nhap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LabelSlideAnimationExample extends JFrame {

    private JButton button;
    private JLabel label;
    private Timer timer;
    private int labelX;
    private final int labelEndX = 300; // Vị trí kết thúc của label (phải)

    public LabelSlideAnimationExample() {
        setTitle("Label Slide Animation Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        button = new JButton("Show Label");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slideLabel();
            }
        });
        add(button);

        label = new JLabel("Sliding Label");
        label.setPreferredSize(new Dimension(200, 100));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.BLACK);
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        labelX = -label.getWidth(); // Vị trí ban đầu của label (nằm bên ngoài khung JFrame)
        label.setLocation(labelX, label.getY());
        add(label);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void slideLabel() {
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelX += 2; // Khoảng cách di chuyển của label
                label.setLocation(labelX, label.getY());
                if (labelX >= labelEndX) {
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LabelSlideAnimationExample();
            }
        });
    }
}
