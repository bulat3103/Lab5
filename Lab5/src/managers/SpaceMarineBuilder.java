package managers;

import data.Chapter;
import data.Coordinates;
import data.Weapon;
import exceptions.NotDeclaredValueException;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class SpaceMarineBuilder {
    private Scanner scanner;

    public SpaceMarineBuilder(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public String askName() {
        String name;
        while (true) {
            try {
                System.out.println("Введите имя:");
                System.out.print(">");
                name = scanner.nextLine().trim();
                if (name.equals("")) throw new NotDeclaredValueException();
                break;
            } catch (NotDeclaredValueException exception) {
                System.out.println("Значение поля 'name' не может быть пустым!");
            }
        }
        return name;
    }

    public int askX() {
        String strX;
        int x;
        while (true) {
            try {
                System.out.println("Введите координату X > -666:");
                System.out.print(">");
                strX = scanner.nextLine().trim();
                x = Integer.parseInt(strX);
                if (x <= -666) throw new NotDeclaredValueException();
                break;
            } catch (NotDeclaredValueException exception) {
                System.out.println("Значение должно быть больше -666!");
            } catch (NumberFormatException exception) {
                System.out.println("Значение 'X' должно быть числом!");
            }
        }
        return x;
    }

    public Float askY() {
        String strY;
        float y;
        while (true) {
            try {
                System.out.println("Введите координату Y > -603:");
                System.out.print(">");
                strY = scanner.nextLine().trim();
                y = Float.parseFloat(strY);
                if (y <= -603) throw new NotDeclaredValueException();
                break;
            } catch (NotDeclaredValueException exception) {
                System.out.println("Значение должно быть больше -666!");
            } catch (NumberFormatException exception) {
                System.out.println("Значение 'X' должно быть числом!");
            }
        }
        return y;
    }

    public Coordinates askCoordinates() {
        int x;
        float y;
        x = askX();
        y = askY();
        return new Coordinates(x, y);
    }

    public int askHealth() {
        String strHealth;
        int health;
        while (true) {
            try {
                System.out.println("Введите здоровье:");
                System.out.print(">");
                strHealth = scanner.nextLine().trim();
                health = Integer.parseInt(strHealth);
                if (health <= 0) throw new NotDeclaredValueException();
                break;
            } catch (NotDeclaredValueException exception) {
                System.out.println("Здоровье должно быть больше нуля!");
            } catch (NumberFormatException exception) {
                System.out.println("Значение 'health' должно быть целым числом!");
            }
        }
        return health;
    }

    public Integer askHeartCount() {
        String strHeartCount;
        int heartCount;
        while (true) {
            try {
                System.out.println("Введите кол-во сердец:");
                System.out.print(">");
                strHeartCount = scanner.nextLine().trim();
                heartCount = Integer.parseInt(strHeartCount);
                if (heartCount < 0 || heartCount > 3) throw new NotDeclaredValueException();
                break;
            } catch (NotDeclaredValueException exception) {
                System.out.println("Количество сердец может быть от 1 до 3");
            } catch (NumberFormatException exception) {
                System.out.println("Значение 'heartCount' должно быть целым числом!");
            }
        }
        return heartCount;
    }

    public String askAchievements() {
        String achieve;
        while (true) {
            try {
                System.out.println("Введите достижение:");
                System.out.print(">");
                achieve = scanner.nextLine().trim();
                if (achieve.equals("")) throw new NotDeclaredValueException();
                break;
            } catch (NotDeclaredValueException exception) {
                System.out.println("Значение поля 'achievements' не может быть пустым!");
            }
        }
        return achieve;
    }

    public Weapon askWeapon() {
        String strWeapon;
        Weapon weapon;
        while (true) {
            try {
                System.out.println("Список доступного оружия - " + Weapon.list());
                System.out.println("Введите оружие");
                System.out.print(">");
                strWeapon = scanner.nextLine().trim();
                weapon = Weapon.valueOf(strWeapon.toUpperCase());
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println("Такого оружия нет!");
            }
        }
        return weapon;
    }

    public Chapter askChapter() {
        return new Chapter(askChapterName(), askChapterParentLegion());
    }

    public String askChapterName() {
        String name;
        while (true) {
            try {
                System.out.println("Введите имя ордена:");
                System.out.print(">");
                name = scanner.nextLine().trim();
                if (name.equals("")) throw new NotDeclaredValueException();
                break;
            } catch (NotDeclaredValueException exception) {
                System.out.println("Значение поля 'name' не может быть пустым!");
            }
        }
        return name;
    }

    public String askChapterParentLegion() {
        String parentLegion;
        while (true) {
            System.out.println("Введите имя родительского легиона:");
            System.out.print(">");
            parentLegion = scanner.nextLine().trim();
            if (parentLegion.equals("")) parentLegion = null;
            break;
        }
        return parentLegion;
    }

    public boolean askAboutChangingField(String ask) {
        String res = ask + " (+/-):";
        String answer;
        while (true) {
            try {
                System.out.println(res);
                System.out.print(">");
                answer = scanner.nextLine().trim();
                if (!answer.equals("+") && !answer.equals("-")) throw new NotDeclaredValueException();
                break;
            } catch (NotDeclaredValueException exception) {
                System.out.println("Ответ должен быть представлен знаками '+' или '-'!");
            }
        }
        return answer.equals("+");
    }
}
