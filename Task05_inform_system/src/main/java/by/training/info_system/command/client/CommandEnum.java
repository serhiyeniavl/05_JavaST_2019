package by.training.info_system.command.client;

import by.training.info_system.command.Command;
import by.training.info_system.command.SignInCommand;

public enum CommandEnum {
    SIGNIN(new SignInCommand());

    Command command;

    CommandEnum(final Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
