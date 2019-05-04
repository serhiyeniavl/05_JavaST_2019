package by.training.info_system.command.client;

import by.training.info_system.command.Command;
import by.training.info_system.command.SignInCommand;
import by.training.info_system.command.SignOutCommand;
import by.training.info_system.command.SignUpCommand;

public enum CommandEnum {
    SIGNIN(new SignInCommand()),
    SIGNOUT(new SignOutCommand()),
    SIGNUP(new SignUpCommand());

    Command command;

    CommandEnum(final Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
