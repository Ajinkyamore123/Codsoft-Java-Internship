package converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

import org.json.JSONObject;

public class CurrencyConverter extends JFrame implements ActionListener {

    JLabel titleLabel, amountLabel, fromLabel, toLabel, resultLabel;

    JTextField amountField;

    JComboBox<String> fromCurrency, toCurrency;

    JButton convertButton, swapButton, clearButton;

    JTextArea historyArea;

    DecimalFormat df = new DecimalFormat("#,##0.00");

    String[] currencies = {
            "USD", "INR", "EUR", "GBP", "JPY",
            "AUD", "CAD", "CHF", "CNY", "SGD"
    };

    public CurrencyConverter() {

        setTitle("Advanced Currency Converter");
        setSize(700, 600);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(230, 240, 255));

        titleLabel = new JLabel("Currency Converter");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBounds(200, 20, 300, 40);
        add(titleLabel);

        amountLabel = new JLabel("Enter Amount:");
        amountLabel.setBounds(80, 100, 120, 25);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(220, 100, 200, 30);
        add(amountField);

        fromLabel = new JLabel("From Currency:");
        fromLabel.setBounds(80, 160, 120, 25);
        add(fromLabel);

        fromCurrency = new JComboBox<>(currencies);
        fromCurrency.setBounds(220, 160, 150, 30);
        add(fromCurrency);

        toLabel = new JLabel("To Currency:");
        toLabel.setBounds(80, 220, 120, 25);
        add(toLabel);

        toCurrency = new JComboBox<>(currencies);
        toCurrency.setBounds(220, 220, 150, 30);
        add(toCurrency);

        swapButton = new JButton("Swap");
        swapButton.setBounds(400, 190, 100, 35);
        swapButton.addActionListener(this);
        add(swapButton);

        convertButton = new JButton("Convert");
        convertButton.setBounds(180, 300, 120, 40);
        convertButton.addActionListener(this);
        add(convertButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(350, 300, 120, 40);
        clearButton.addActionListener(this);
        add(clearButton);

        resultLabel = new JLabel("Converted Amount : ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        resultLabel.setBounds(80, 370, 500, 30);
        add(resultLabel);

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane pane = new JScrollPane(historyArea);
        pane.setBounds(80, 430, 520, 100);
        add(pane);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == swapButton) {

            String temp = fromCurrency.getSelectedItem().toString();

            fromCurrency.setSelectedItem(
                    toCurrency.getSelectedItem());

            toCurrency.setSelectedItem(temp);
        }

        else if (e.getSource() == clearButton) {

            amountField.setText("");
            resultLabel.setText("Converted Amount : ");
        }

        else if (e.getSource() == convertButton) {

            try {

                double amount =
                        Double.parseDouble(amountField.getText());

                String from =
                        fromCurrency.getSelectedItem().toString();

                String to =
                        toCurrency.getSelectedItem().toString();

                String apiURL =
                        "https://api.exchangerate-api.com/v4/latest/"
                                + from;

                URL url = new URL(apiURL);

                HttpURLConnection conn =
                        (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("GET");

                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(
                                        conn.getInputStream()));

                StringBuilder response = new StringBuilder();

                String line;

                while ((line = reader.readLine()) != null) {

                    response.append(line);
                }

                reader.close();

                JSONObject jsonObject =
                        new JSONObject(response.toString());

                JSONObject rates =
                        jsonObject.getJSONObject("rates");

                double rate =
                        rates.getDouble(to);

                double convertedAmount =
                        amount * rate;

                resultLabel.setText(
                        "Converted Amount : "
                                + df.format(convertedAmount)
                                + " " + to);

                historyArea.append(
                        LocalDateTime.now()
                                + " : "
                                + amount + " "
                                + from
                                + " → "
                                + df.format(convertedAmount)
                                + " "
                                + to
                                + "\n");

            }

            catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid amount.");

            }

            catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to fetch exchange rate.");
            }

        }

    }

    public static void main(String[] args) {

        new CurrencyConverter();

    }

}