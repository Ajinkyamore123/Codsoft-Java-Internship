package GradeCalculator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class StudentGradeCalculator extends JFrame implements ActionListener {

    JLabel titleLabel, nameLabel, s1Label, s2Label, s3Label, s4Label, s5Label;

    JTextField nameField, s1Field, s2Field, s3Field, s4Field, s5Field;

    JButton calculateButton, resetButton;

    JTable table;
    DefaultTableModel model;

    public StudentGradeCalculator() {

        setTitle("Student Grade Calculator");
        setSize(850, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color bgColor = new Color(240, 248, 255);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(bgColor);

        titleLabel = new JLabel("STUDENT GRADE CALCULATOR");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(230, 20, 400, 30);

        nameLabel = new JLabel("Student Name");
        nameLabel.setBounds(50, 90, 120, 25);

        s1Label = new JLabel("Subject 1");
        s1Label.setBounds(50, 130, 120, 25);

        s2Label = new JLabel("Subject 2");
        s2Label.setBounds(50, 170, 120, 25);

        s3Label = new JLabel("Subject 3");
        s3Label.setBounds(50, 210, 120, 25);

        s4Label = new JLabel("Subject 4");
        s4Label.setBounds(50, 250, 120, 25);

        s5Label = new JLabel("Subject 5");
        s5Label.setBounds(50, 290, 120, 25);

        nameField = new JTextField();
        nameField.setBounds(180, 90, 150, 25);

        s1Field = new JTextField();
        s1Field.setBounds(180, 130, 150, 25);

        s2Field = new JTextField();
        s2Field.setBounds(180, 170, 150, 25);

        s3Field = new JTextField();
        s3Field.setBounds(180, 210, 150, 25);

        s4Field = new JTextField();
        s4Field.setBounds(180, 250, 150, 25);

        s5Field = new JTextField();
        s5Field.setBounds(180, 290, 150, 25);

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(70, 350, 120, 35);
        calculateButton.addActionListener(this);

        resetButton = new JButton("Reset");
        resetButton.setBounds(220, 350, 120, 35);
        resetButton.addActionListener(this);

        String columns[] = {
                "Name",
                "Total",
                "Percentage",
                "Grade",
                "Result"
        };

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(400, 100, 380, 250);

        panel.add(titleLabel);
        panel.add(nameLabel);
        panel.add(s1Label);
        panel.add(s2Label);
        panel.add(s3Label);
        panel.add(s4Label);
        panel.add(s5Label);

        panel.add(nameField);
        panel.add(s1Field);
        panel.add(s2Field);
        panel.add(s3Field);
        panel.add(s4Field);
        panel.add(s5Field);

        panel.add(calculateButton);
        panel.add(resetButton);

        panel.add(pane);

        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == calculateButton) {

            try {

                String name = nameField.getText();

                int m1 = Integer.parseInt(s1Field.getText());
                int m2 = Integer.parseInt(s2Field.getText());
                int m3 = Integer.parseInt(s3Field.getText());
                int m4 = Integer.parseInt(s4Field.getText());
                int m5 = Integer.parseInt(s5Field.getText());

                if (m1 > 100 || m2 > 100 || m3 > 100 || m4 > 100 || m5 > 100
                        || m1 < 0 || m2 < 0 || m3 < 0 || m4 < 0 || m5 < 0) {

                    JOptionPane.showMessageDialog(this,
                            "Marks should be between 0 and 100");
                    return;
                }

                int total = m1 + m2 + m3 + m4 + m5;

                double percentage = total / 5.0;

                String grade;

                if (percentage >= 90)
                    grade = "A+";
                else if (percentage >= 80)
                    grade = "A";
                else if (percentage >= 70)
                    grade = "B";
                else if (percentage >= 60)
                    grade = "C";
                else if (percentage >= 50)
                    grade = "D";
                else
                    grade = "F";

                String result;

                if (percentage >= 40)
                    result = "PASS";
                else
                    result = "FAIL";

                model.addRow(new Object[]{
                        name,
                        total,
                        String.format("%.2f", percentage) + "%",
                        grade,
                        result
                });

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this,
                        "Please enter valid numbers.");
            }
        }

        if (e.getSource() == resetButton) {

            nameField.setText("");
            s1Field.setText("");
            s2Field.setText("");
            s3Field.setText("");
            s4Field.setText("");
            s5Field.setText("");

            model.setRowCount(0);
        }
    }

    public static void main(String[] args) {

        new StudentGradeCalculator();
    }
}