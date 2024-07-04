import javax.swing.*;
import java.awt.*;

public class EquipmentRegistrationGUI {

    public static void main(String[] args) {
        // Создание окна регистрации оборудования
        JFrame frame = new JFrame("Регистрация оборудования");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Создание панели для ввода данных
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Создание полей ввода
        JTextField nameField;
        nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        panel.add(new JLabel("Название:"));
        panel.add(nameField);

        JTextField serialNumberField = new JTextField();
        serialNumberField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        panel.add(new JLabel("Серийный номер:"));
        panel.add(serialNumberField);

        JComboBox<String> typeComboBox;
        typeComboBox = new JComboBox<>(new String[] {"ПК", "Ноутбук", "Сервер"});
        typeComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        panel.add(new JLabel("Тип:"));
        panel.add(typeComboBox);

        // Создание кнопки регистрации
        JButton registerButton = new JButton("Зарегистрировать");
        registerButton.addActionListener(_ -> {
            // Получение введенных данных
            nameField.getText();
            serialNumberField.getText();
            typeComboBox.getSelectedItem();

            // Отправка данных на сервер для регистрации оборудования
            // ...

            // Вывод сообщения об успешной регистрации
            JOptionPane.showMessageDialog(frame, "Оборудование успешно зарегистрировано!");

            // Очистка полей ввода
            nameField.setText("");
            serialNumberField.setText("");
            typeComboBox.setSelectedIndex(0);
        });
        panel.add(registerButton);

        // Добавление панели в окно и отображение окна
        frame.add(panel);
        frame.setVisible(true);
    }
}


