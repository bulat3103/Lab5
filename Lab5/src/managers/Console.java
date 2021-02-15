package managers;

import exceptions.ScriptRecursionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Operates command input.
 */
public class Console {
    private CommandManager commandManager;
    private SpaceMarineBuilder builder;
    private Scanner scanner;
    private List<String> scriptFileNames = new ArrayList<>();

    public Console(CommandManager commandManager, Scanner scanner, SpaceMarineBuilder builder) {
        this.commandManager = commandManager;
        this.scanner = scanner;
        this.builder = builder;
    }

    /**
     * Mode for catching commands from user input.
     */
    public void interactiveMode() {
        String[] userCommand = {"", ""};
        int commandStatus;
        try {
            do {
                System.out.print("$ ");
                userCommand = (scanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandManager.addToHistory(userCommand[0]);
                commandStatus = executeCommand(userCommand);
            } while (commandStatus != 2);
        } catch (Exception e) {

        }
    }

    /**
     * Mode for catching commands from a script.
     * @param fileName Its argument.
     * @return Exit code.
     */
    public int scriptMode(String fileName) {
        String[] userCommand = {"", ""};
        int commandStatus;
        scriptFileNames.add(fileName);
        try (Scanner scriptScanner = new Scanner(new File(fileName))){
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner oldScanner = builder.getScanner();
            builder.setScanner(scriptScanner);
            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                System.out.println("Выполняется команда: " + userCommand[0]);
                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptFileNames) {
                        if (userCommand[1].equals(script)) throw new ScriptRecursionException();
                    }
                }
                commandStatus = executeCommand(userCommand);
            } while (commandStatus == 0 && scriptScanner.hasNextLine());
            builder.setScanner(oldScanner);
            return commandStatus;
        } catch (FileNotFoundException e) {
            System.out.println("Файл со скриптом не найден!");
        } catch (NoSuchElementException e) {
            System.out.println("Файл со скриптом пуст!");
        } catch (ScriptRecursionException e) {
            System.out.println("Скрипты не могут вызываться рекурсивно");
        }
        return 1;
    }

    /**
     * Launchs the command.
     * @param userCommand Command to launch.
     * @return Exit code.
     */
    private int executeCommand(String[] userCommand) {
        switch (userCommand[0]) {
            case "":
                break;
            case "help":
                if (!commandManager.help(userCommand[1])) return 1;
                break;
            case "info":
                if (!commandManager.info(userCommand[1])) return 1;
                break;
            case "show":
                if (!commandManager.show(userCommand[1])) return 1;
                break;
            case "insert":
                if (!commandManager.insert(userCommand[1])) return 1;
                break;
            case "update":
                if (!commandManager.update(userCommand[1])) return 1;
                break;
            case "remove_key":
                if (!commandManager.removeKey(userCommand[1])) return 1;
                break;
            case "clear":
                if (!commandManager.clear(userCommand[1])) return 1;
                break;
            case "save":
                if (!commandManager.save(userCommand[1])) return 1;
                break;
            case "execute_script":
                if (!commandManager.executeScript(userCommand[1])) return 1;
                else return scriptMode(userCommand[1]);
            case "remove_greater":
                if (!commandManager.removeGreater(userCommand[1])) return 1;
                break;
            case "history":
                if (!commandManager.history(userCommand[1])) return 1;
                break;
            case "remove_lower_key":
                if (!commandManager.removeLowerKey(userCommand[1])) return 1;
                break;
            case "remove_all_by_weapon_type":
                if (!commandManager.removeAllByWeaponType(userCommand[1])) return 1;
                break;
            case "sum_of_health":
                if (!commandManager.sumOfHealth(userCommand[1])) return 1;
                break;
            case "average_of_heart_count":
                if (!commandManager.averageOfHeartCount(userCommand[1])) return 1;
                break;
            case "exit":
                if (!commandManager.exit(userCommand[1])) return 1;
                else return 2;
            default:
                System.out.println("Команда '" + userCommand[1] + "' не найдена. Наберите 'help' для справки.");
                return 1;
        }
        return 0;
    }
}
