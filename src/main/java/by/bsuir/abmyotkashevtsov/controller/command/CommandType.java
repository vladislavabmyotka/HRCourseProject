package by.bsuir.abmyotkashevtsov.controller.command;

/**
 *  In this enumeration lists the values in uppercase are located in the hidden field of the command type in all jsp.
 */
public enum CommandType {
    ;
    private Command command;

    /**
     * Constructs and initializes a Command.
     * @param command - command that came with jsp.
     */
    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
