package by.training.info_system.command.client;

import by.training.info_system.command.ActionCommand;
import by.training.info_system.command.SignInCommand;

public enum CommandEnum {
    SIGNIN(new SignInCommand());

    ActionCommand command;

    CommandEnum(final ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
