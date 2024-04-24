import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class Frame extends JFrame {
    private int pink = 0;
    private int red = 1;
    private int green = 2;
    private int blue = 3;
    private int yellow = 4;

    private int[][] currentArray;
    private int rows;
    private int cols;
    private final Map<Integer, Color> numberColorMap;
    private JPanel colorPanel;
    private void changeArrayAndRefresh(int n) {

        Logic.changeColore(currentArray,n);
        Logic.rules(currentArray);
        colorPanel.removeAll();
        colorPanel.add(createColorPanel());
        colorPanel.revalidate();
        colorPanel.repaint();

        if (Logic.endGame()) {
            int option = JOptionPane.showOptionDialog(
                    this,
                    "Game over! Choose an option:",
                    "Game Over",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Restart", "Exit"},
                    "Restart");

            if (option == JOptionPane.YES_OPTION) {
                rows = promptForInteger("Enter the number of rows:");
                cols = promptForInteger("Enter the number of columns:");
                currentArray = Logic.makeRandomArray(rows,cols);
                changeArrayAndRefresh(0);

            } else if (option == JOptionPane.NO_OPTION) {
                dispose();
            }
        }
    }
    private Map<Integer, Color> createColorMap() {
        Map<Integer, Color> colorMap = new HashMap<>();
        colorMap.put(0, Color.PINK);
        colorMap.put(1, Color.RED);
        colorMap.put(2, Color.GREEN);
        colorMap.put(3, Color.BLUE);
        colorMap.put(4, Color.YELLOW);
        return colorMap;
    }
    private int promptForInteger(String message) {
        String input = JOptionPane.showInputDialog(this, message);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return promptForInteger("Invalid input! " + message);
        }
    }
    private JPanel createColorPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        for (int i = 0; i < currentArray.length; i++) {
            for (int j = 0; j < currentArray[i].length; j++) {
                JPanel colorPanel = new JPanel();

                Dimension cellSize = new Dimension(50, 50);
                Dimension cellMinimumSize = new Dimension(20, 20);
                Dimension cellMaximumSize = new Dimension(70, 70);
                colorPanel.setPreferredSize(cellSize);
                colorPanel.setMaximumSize(cellMaximumSize);
                colorPanel.setMinimumSize(cellMinimumSize);

                colorPanel.setBackground(numberColorMap.get(currentArray[i][j]));
                colorPanel.setBorder(BorderFactory.createEtchedBorder());
                panel.add(colorPanel, gbc);
                gbc.gridx++;
            }
            gbc.gridy++;
            gbc.gridx = 0;
        }

        return panel;
    }

    public Frame() {
        setTitle("Перекраска");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        rows = promptForInteger("Enter the number of rows:");
        cols = promptForInteger("Enter the number of columns:");
        currentArray = Logic.makeRandomArray(rows,cols);

        numberColorMap = createColorMap();

        colorPanel = createColorPanel();
        add(colorPanel, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel(new GridLayout(1, 0));

        JButton changeArrayButton0 = new JButton("PINK");
        changeArrayButton0.addActionListener(e -> changeArrayAndRefresh(pink));
        buttonPanel.add(changeArrayButton0);

        JButton changeArrayButton1 = new JButton("RED");
        changeArrayButton1.addActionListener(e -> changeArrayAndRefresh(red));
        buttonPanel.add(changeArrayButton1);

        JButton changeArrayButton2 = new JButton("GREEN");
        changeArrayButton2.addActionListener(e -> changeArrayAndRefresh(green));
        buttonPanel.add(changeArrayButton2);

        JButton changeArrayButton3 = new JButton("BLUE");
        changeArrayButton3.addActionListener(e -> changeArrayAndRefresh(blue));
        buttonPanel.add(changeArrayButton3);

        JButton changeArrayButton4 = new JButton("YELLOW");
        changeArrayButton4.addActionListener(e -> changeArrayAndRefresh(yellow));
        buttonPanel.add(changeArrayButton4);

        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

}
