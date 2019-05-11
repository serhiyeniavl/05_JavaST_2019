package by.training.info_system.command.client;

import by.training.info_system.command.*;
import by.training.info_system.command.manager.ManagerOrderCommand;
import by.training.info_system.command.user.RentCarCommand;
import by.training.info_system.command.user.SignInCommand;
import by.training.info_system.command.user.SignOutCommand;
import by.training.info_system.command.user.SignUpCommand;

public enum CommandEnum {
    SIGNIN(new SignInCommand()),
    SIGNOUT(new SignOutCommand()),
    SIGNUP(new SignUpCommand()),
    MANAGE_ORDER(new ManagerOrderCommand()),
    RENT_CAR(new RentCarCommand());

    Command command;

    CommandEnum(final Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
