package data;

import java.time.LocalDateTime;
import java.util.Objects;

public class SpaceMarine implements Comparable<SpaceMarine>{
    private int id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDateTime creationDate;
    private int health;
    private Integer heartCount;
    private String achievements;
    private Weapon weaponType;
    private Chapter chapter;

    public SpaceMarine(int id, String name, Coordinates coordinates, LocalDateTime creationDate, int health, Integer heartCount, String achievements, Weapon weaponType, Chapter chapter) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.health = health;
        this.heartCount = heartCount;
        this.achievements = achievements;
        this.weaponType = weaponType;
        this.chapter = chapter;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public int getHealth() {
        return health;
    }

    public Integer getHeartCount() {
        return heartCount;
    }

    public String getAchievements() {
        return achievements;
    }

    public Weapon getWeaponType() {
        return weaponType;
    }

    public Chapter getChapter() {
        return chapter;
    }

    @Override
    public int compareTo(SpaceMarine o) {
        if (this.health == o.health) {
            return Integer.compare(this.heartCount, o.heartCount);
        }
        return Integer.compare(this.health, o.health);
    }

    @Override
    public String toString() {
        String info = "";
        info += "Космический десант №" + id;
        info += " (добавлен " + creationDate.toLocalDate() + " " + creationDate.toLocalTime() + ")";
        info += "\n Имя: " + name;
        info += "\n Местоположение: " + coordinates;
        info += "\n Здоровье: " + health;
        info += "\n Число сердец: " + heartCount;
        info += "\n Дальнее оружие: " + weaponType;
        info += "\n Дистжения: " + achievements;
        info += "\n Орден: " + chapter;
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceMarine that = (SpaceMarine) o;
        return health == that.health &&
                Objects.equals(name, that.name) &&
                Objects.equals(coordinates, that.coordinates) &&
                Objects.equals(heartCount, that.heartCount) &&
                Objects.equals(achievements, that.achievements) &&
                weaponType == that.weaponType &&
                Objects.equals(chapter, that.chapter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, health, heartCount, achievements, weaponType, chapter);
    }
}
