package data;

public enum Weapon {
    HEAVY_BOLTGUN,
    FLAMER,
    GRENADE_LAUNCHER;

    public static String list() {
        String list = "";
        for (Weapon weapon : values()) {
            list += weapon.name() + ", ";
        }
        return list.substring(0, list.length() - 2);
    }
}
