package com.phishing;

import javax.swing.*;
import java.awt.*;

public class PhishingUI {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Phishing URL Detector");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Phishing URL Detector", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        JLabel urlLabel = new JLabel("Enter URL:");
        JTextField urlField = new JTextField();

        inputPanel.add(urlLabel, BorderLayout.WEST);
        inputPanel.add(urlField, BorderLayout.CENTER);

        JButton checkButton = new JButton("Check URL");
        JLabel resultLabel = new JLabel("Result: ", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        checkButton.addActionListener(e -> {
            String url = urlField.getText().trim();

            if (url.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a URL!");
                return;
            }

            try {
                String result = PhishingDetector.predict(url);
                resultLabel.setText("Result: " + result);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(checkButton, BorderLayout.NORTH);
        bottomPanel.add(resultLabel, BorderLayout.CENTER);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }
}
