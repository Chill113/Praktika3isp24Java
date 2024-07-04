import java.util.ArrayList;
import java.util.List;

public class EquipmentSearch {

    private final List<Equipment123> equipment1List;

    public EquipmentSearch(List<Equipment123> equipment1List) {
        this.equipment1List = equipment1List;
    }

    public List<Equipment123> searchEquipment(String criteria) {
        List<Equipment123> results = new ArrayList<>();
        for (Equipment123 equipment1 : equipment1List) {
            if (equipment1.getName().contains(criteria) || equipment1.getSerialNumber().contains(criteria) || equipment1.getType().contains(criteria)) {
                results.add(equipment1);
            }
        }
        return results;
    }
}
