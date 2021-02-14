package commands;

import exceptions.WrongAmountOfParametersException;

public class HelpCommand extends AbstractCommand{

    public HelpCommand() {
        super("help", "вывести справку по доступным командам");
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
