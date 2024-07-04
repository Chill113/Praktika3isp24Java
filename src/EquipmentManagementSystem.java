import qwe.bel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class EquipmentManagementSystem {

    private JFrame mainFrame;
    private JPanel contentPane;
    private EquipmentManager equipmentManager;

    public EquipmentManagementSystem() {
        equipmentManager = new EquipmentManager();
        initializeGUI();
    }

    private void initializeGUI() {
        mainFrame = new JFrame("Система управления оборудованием");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);

        contentPane = new JPanel(new CardLayout());
        mainFrame.setContentPane(contentPane);

        // Экран добавления нового оборудования
        JPanel addEquipmentPanel = createAddEquipmentPanel();
        contentPane.add(addEquipmentPanel, "addEquipment");

        // Экран обновления состояния/удаления оборудования
        JPanel updateDeleteEquipmentPanel = createUpdateDeleteEquipmentPanel();
        contentPane.add(updateDeleteEquipmentPanel, "updateDelete");

        // Экран поиска оборудования
        JPanel searchEquipmentPanel = createSearchEquipmentPanel();
        contentPane.add(searchEquipmentPanel, "search");

        // Экран просмотра отчетов
        JPanel reportPanel = createReportPanel();
        contentPane.add(reportPanel, "reports");

        // Начальный экран - добавление оборудования
        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        cardLayout.show(contentPane, "addEquipment");

        mainFrame.setVisible(true);
    }

    private JPanel createAddEquipmentPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Добавление нового оборудования"));

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Название:");
        JTextField nameField = new JTextField();
        JLabel typeLabel = new JLabel("Тип:");
        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"ПК", "Ноутбук", "Сервер"});
        JButton addButton = new JButton("Добавить");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                String type = (String) typeComboBox.getSelectedItem();
                if (!id.isEmpty() && !name.isEmpty() && !type.isEmpty()) {
                    Equipment123 newEquipment1 = new Equipment123(id, name, type);
                    equipmentManager.addEquipment(newEquipment1);
                    JOptionPane.showMessageDialog(mainFrame, "Оборудование успешно добавлено!");
                    idField.setText("");
                    nameField.setText("");
                    typeComboBox.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Пожалуйста, заполните все поля.");
                }
            }
        });

        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(typeLabel);
        panel.add(typeComboBox);
        panel.add(new JLabel()); // Заполняем пустую ячейку
        panel.add(addButton);

        return panel;
    }

    private JPanel createUpdateDeleteEquipmentPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Обновление/Удаление оборудования"));

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        AtomicReference<Object> JLa = null;
        bel nameLabel;
        nameLabel = new bel("Новое название:");
        JTextField nameField = new JTextField();
        JLabel typeLabel = new JLabel("Новый тип:");
        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"ПК", "Ноутбук", "Сервер"});
        JButton updateButton = new JButton("Обновить");
        JButton deleteButton = new JButton("Удалить");

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                String type = (String) typeComboBox.getSelectedItem();
                if (!id.isEmpty()) {
                    equipmentManager.updateEquipment(id, name, type);
                    JOptionPane.showMessageDialog(mainFrame, "Оборудование успешно обновлено!");
                    idField.setText("");
                    nameField.setText("");
                    typeComboBox.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Пожалуйста, введите ID оборудования.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                if (!id.isEmpty()) {
                    equipmentManager.deleteEquipment(id);
                    JOptionPane.showMessageDialog(mainFrame, "Оборудование успешно удалено!");
                    idField.setText("");
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Пожалуйста, введите ID оборудования.");
                }
            }
        });

        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameField);
        panel.add(typeLabel);
        panel.add(typeComboBox);
        panel.add(updateButton);
        panel.add(deleteButton);

        return panel;
    }

    private JPanel createSearchEquipmentPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Поиск оборудования"));

        JLabel searchLabel = new JLabel("Поиск по:");
        JComboBox<String> searchTypeComboBox = new JComboBox<>(new String[]{"ID", "Название", "Тип"});
        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Поиск");
        JTextArea resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        JScrollPane resultsScrollPane = new JScrollPane(resultsArea);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchType = (String) searchTypeComboBox.getSelectedItem();
                String searchValue = searchField.getText();
                List<Equipment123> results = equipmentManager.searchEquipment(searchType, searchValue);
                resultsArea.setText("");
                if (!results.isEmpty()) {
                    for (Equipment123 equipment1 : results) {
                        resultsArea.append("ID: " + equipment1.getId() + ", Название: " + equipment1.getName() + ", Тип: " + equipment1.getType() + "\n");
                    }
                } else {
                    resultsArea.append("Оборудование не найдено.");
                }
            }
        });

        panel.add(searchLabel);
        panel.add(searchTypeComboBox);
        panel.add(new JLabel("Значение:"));
        panel.add(searchField);
        panel.add(searchButton);
        panel.add(resultsScrollPane);

        return panel;
    }

    private JPanel createReportPanel() {
        JPanel panel
                = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Просмотр отчетов"));

        // В этом примере показываем пример - добавить реальную логику отчетов
        JTextArea reportArea = new JTextArea("Пример отчета: \n" +
                "Список оборудования: \n" +
                equipmentManager.getEquipmentListAsString());
        reportArea.setEditable(false);
        JScrollPane reportScrollPane = new JScrollPane(reportArea);

        panel.add(reportScrollPane, BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        new EquipmentManagementSystem();
    }
}

class Equipment123 {
    private String id;
    private String name;
    private String type;

    public Equipment123(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {

    }

    public void setName() {

    }
}

class EquipmentManager {
    private List<Equipment123> equipment1List;

    public EquipmentManager() {
        equipment1List = new ArrayList<>();
    }

    public void addEquipment(Equipment123 equipment1) {
        equipment1List.add(equipment1);
    }

    public void updateEquipment(String id, String name, String type) {
        for (int i = 0; i < equipment1List.size(); i++) {
            if (equipment1List.get(i).getId().equals(id)) {
                equipment1List.get(i).setName();
                equipment1List.get(i).setType(type);
                return;
            }
        }
    }

    public void deleteEquipment(String id) {
        for (int i = 0; i < equipment1List.size(); i++) {
            if (equipment1List.get(i).getId().equals(id)) {
                equipment1List.remove(i);
                return;
            }
        }
    }

    public List<Equipment123> searchEquipment(String searchType, String searchValue) {
        List<Equipment123> results = new ArrayList<>();
        for (Equipment123 equipment1 : equipment1List) {
            if (searchType.equals("ID") && equipment1.getId().equals(searchValue)) {
                results.add(equipment1);
            } else if (searchType.equals("Название") && equipment1.getName().contains(searchValue)) {
                results.add(equipment1);
            } else if (searchType.equals("Тип") && equipment1.getType().equals(searchValue)) {
                results.add(equipment1);
            }
        }
        return results;
    }

    public String getEquipmentListAsString() {
        StringBuilder sb = new StringBuilder();
        for (Equipment123 equipment1 : equipment1List) {
            sb.append("ID: ").append(equipment1.getId()).append(", Название: ").append(equipment1.getName()).append(", Тип: ").append(equipment1.getType()).append("\n");
        }
        return sb.toString();
    }
}