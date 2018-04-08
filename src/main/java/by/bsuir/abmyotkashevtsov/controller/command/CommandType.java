package by.bsuir.abmyotkashevtsov.controller.command;

import by.bsuir.abmyotkashevtsov.controller.command.impl.*;
import by.bsuir.abmyotkashevtsov.controller.command.impl.accountImpl.*;
import by.bsuir.abmyotkashevtsov.service.AccountService;

/**
 *  In this enumeration lists the values in uppercase are located in the hidden field of the command type in all jsp.
 */
public enum CommandType {
    INDEX(new IndexCommand()),
    REGISTER_RELOAD(new RegisterReloadCommand()),
    AUTHORIZATION(new AuthorizationCommand(new AccountService())),
    REGISTER(new RegisterCommand(new AccountService())),
    EDIT_ACCOUNT_DATA(new AccountEditCommand(new AccountService())),
    DELETE_ACCOUNT(new AccountDeleteCommand(new AccountService())),
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
