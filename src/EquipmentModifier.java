public class EquipmentModifier {

    public void updateEquipmentName(String id, String newName) {
        Equipment123 equipment1 = getEquipment(id);
        assert equipment1 != null;
        equipment1.setName();
    }

    public void updateEquipmentType(String id, String newType) {
        Equipment123 equipment1 = getEquipment(id);
        assert equipment1 != null;
        equipment1.setType(newType);
    }

    // ...

    private Equipment123 getEquipment(String id) {
        // Получение оборудования из базы данных или другого источника
        return null;
    }
}
