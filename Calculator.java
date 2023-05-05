import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator implements ActionListener {

    private JFrame frame;
    private JTextField textField;
    private JButton[] buttons;
    private String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            ".", "0", "=", "+"
    };
    private double firstNumber = 0;
    private String operator = "";

    public Calculator() {
        // create the main frame
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 450);
        frame.setLayout(new BorderLayout());

        // create the text field for displaying the results
        textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setPreferredSize(new Dimension(300, 50)); // set preferred size here
        frame.add(textField, BorderLayout.NORTH);

        // create the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        // create the buttons and add them to the panel
        buttons = new JButton[buttonLabels.length];
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
        }

        // add the button panel to the frame
        frame.add(buttonPanel, BorderLayout.CENTER);

        // show the frame
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]|\\.")) {
            // if the button is a number or a decimal point
            textField.setText(textField.getText() + command);
        } else if (command.matches("[+\\-*/]")) {
            // if the button is an operator
            firstNumber = Double.parseDouble(textField.getText());
            operator = command;
            textField.setText("");
        } else if (command.equals("=")) {
            // if the button is the equals sign
            double secondNumber = Double.parseDouble(textField.getText());
            double result = 0;
            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    result = firstNumber / secondNumber;
                    break;
            }
            textField.setText(Double.toString(result));
        } else if (command.equals("C")) {
            // if the button is the clear button
            textField.setText("");
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
