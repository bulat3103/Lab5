package managers;

import data.SpaceMarine;
import data.Weapon;
import managers.FileManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CollectionManager {
    private TreeMap<Integer, SpaceMarine> marines = new TreeMap<>();
    private LocalDateTime lastInitTime;
    private FileManager fileManager;

    public CollectionManager(FileManager fileManager) {
        this.lastInitTime = null;
        this.fileManager = fileManager;

        loadCollection();
    }

    private void loadCollection() {
        marines = fileManager.readCollection();
        lastInitTime = LocalDateTime.now();
    }

    public void addToCollection(int key, SpaceMarine marine) {
        marines.put(key, marine);
    }

    public int generateId() {
        if (marines.isEmpty()) return 1;
        int lastId = 0;
        for (SpaceMarine marine : marines.values()) {
            lastId = Math.max(lastId, marine.getId());
        }
        return lastId + 1;
    }

    public String collectionType() {
        return marines.getClass().getName();
    }

    public void clearCollection() {
        marines.clear();
    }

    public void saveCollection() {
        fileManager.writeCollection(marines);
    }

    public List<Integer> getGreater(SpaceMarine o) {
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, SpaceMarine> e : marines.entrySet()) {
            if (e.getValue().compareTo(o) > 0) list.add(e.getKey());
        }
        return list;
    }

    public List<Integer> getLowerKey(int key) {
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, SpaceMarine> e : marines.entrySet()) {
            if (e.getKey() < key) list.add(e.getKey());
        }
        return list;
    }

    public List<Integer> getKeyByWeaponType(Weapon weapon) {
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, SpaceMarine> e : marines.entrySet()) {
            if (e.getValue().getWeaponType().equals(weapon)) list.add(e.getKey());
        }
        return list;
    }

    public void removeFromCollection(int key) {
        marines.remove(key);
    }

    public int collectionSize() {
        return marines.size();
    }

    public SpaceMarine getFromCollection(int key) {
        return marines.get(key);
    }

    public Integer getKeyById(int id) {
        for (Map.Entry<Integer, SpaceMarine> e : marines.entrySet()) {
            if (e.getValue().getId() == id) return e.getKey();
        }
        return null;
    }

    public double getSumOfHealth() {
        double sum_of_health = 0;
        for (SpaceMarine e : marines.values()) {
            sum_of_health += e.getHealth();
        }
        return sum_of_health;
    }

    public double averageOfHealthCount() {
        double sum = 0;
        for (SpaceMarine e : marines.values()) {
            sum += e.getHeartCount();
        }
        if (sum == 0) return 0;
        return sum / marines.size();
    }

    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    @Override
    public String toString() {
        if (marines.isEmpty()) return "Коллекция пуста!";
        String info = "";
        int count = 0;
        for (SpaceMarine marine : marines.values()) {
            info += marine.toString();
            if (count != marines.size() - 1) info += "\n\n";
            count++;
        }
        return info;
    }
}
