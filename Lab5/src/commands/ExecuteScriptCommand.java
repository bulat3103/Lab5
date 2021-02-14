package commands;

import exceptions.WrongAmountOfParametersException;

public class ExecuteScriptCommand extends AbstractCommand{

    public ExecuteScriptCommand() {
        super("execute_script <file_name>", "исполнить скрипт из указанного файла");
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfParametersException();
            System.out.println("Скрипт выполняется!");
            return true;
        } catch (WrongAmountOfParametersException exception) {
            System.out.println("Вместе с этой командой должен быть передан параметр! Наберит 'help' для справки");
        }
        return false;
    }
}
