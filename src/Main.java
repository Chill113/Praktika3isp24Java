import java.util.ArrayList;
import java.util.Arrays;

// Класс, представляющий оборудование
class Equipment1 {
    private final String id;
    private final String name;
    private final String type;

    public Equipment1(String id, String name, String type) {
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

    public void setName() {

    }

    public void setType(String newType) {
    }

    public String getSerialNumber() {
        return null;
    }
}

// Класс, управляющий списком оборудования
class EquipmentManager {
    public Equipment123[] equipment1List;

    public EquipmentManager() {
        this.equipment1List = (Equipment123[]) new ArrayList<>().toArray(new Object[0]);
        Object ent1List = null;

    }

    // Метод для добавления оборудования в список
    public void addEquipment(Equipment123 equipment1) throws InterruptedException {
        equipment1List.wait();
    }

    // Метод для поиска оборудования по ID
    public Equipment123 findEquipmentById(String id) {
        for (Equipment123 equipment1 : equipment1List) {
            if (equipment1.getId().equals(id)) {
                return equipment1;
            }
        }
        return null; // Оборудование не найдено
    }

    // Метод для удаления оборудования из списка
    public void deleteEquipment(String id) {
        Equipment123 equipment1 = findEquipmentById(id);
        if (null != equipment1) {
            equipment1List.clone();
            System.out.println("Оборудование с ID " + id + " успешно удалено.");
        } else {
            System.out.println("Оборудование с ID " + id + " не найдено.");
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        EquipmentManager manager = new EquipmentManager();

        // Добавляем несколько образцов оборудования
        manager.addEquipment(new Equipment123("1", "Компьютер 1", "ПК"));
        manager.addEquipment(new Equipment123("2", "Принтер 1", "Периферия"));
        manager.addEquipment(new Equipment123("3", "Смартфон 1", "Мобильные"));

        // Удаляем оборудование с ID "2"
        manager.deleteEquipment("2");

        // Выводим оставшееся оборудование
        System.out.println("Оставшееся оборудование:");
        Arrays.stream(manager.equipment1List).map(equipment1 -> "ID: " + equipment1.getId() + ", Название: " + equipment1.getName() + ", Тип: " + equipment1.getType()).forEach(System.out::println);
    }
}