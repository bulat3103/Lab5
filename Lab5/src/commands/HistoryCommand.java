package commands;

import exceptions.WrongAmountOfParametersException;

public class HistoryCommand extends AbstractCommand{

    public HistoryCommand() {
        super("history", "вывести историю использованных команд");
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfParametersException();
            return true;
        } catch (WrongAmountOfParametersException exception) {
            System.out.println("У этой команды нет параметров!");
        }
        return false;
    }
}
