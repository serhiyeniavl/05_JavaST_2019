package by.training.info_system.command.client;

import by.training.info_system.command.Command;
import by.training.info_system.command.manager.ConfirmOrderManagerCommand;
import by.training.info_system.command.manager.DenyOrderManagerCommand;
import by.training.info_system.command.user.*;

public enum CommandEnum {
    SIGNIN(new SignInCommand()),
    SIGNOUT(new SignOutCommand()),
    SIGNUP(new SignUpCommand()),
    CONFIRM_ORDER(new ConfirmOrderManagerCommand()),
    DENY_ORDER(new DenyOrderManagerCommand()),
    ACCEPT_ORDER(new AcceptOrderCommand()),
    RENT_CAR(new RentCarCommand());

    Command command;

    CommandEnum(final Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
