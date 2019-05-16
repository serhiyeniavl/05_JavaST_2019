package by.training.info_system.command.client;

import by.training.info_system.command.Command;
import by.training.info_system.command.admin.MakeManagerAnUserCommand;
import by.training.info_system.command.admin.MakeUserAManagerCommand;
import by.training.info_system.command.manager.CompleteUserOrderCommand;
import by.training.info_system.command.manager.ConfirmOrderManagerCommand;
import by.training.info_system.command.manager.DenyOrderManagerCommand;
import by.training.info_system.command.manager.ExtendUserOrderCommand;
import by.training.info_system.command.user.*;

public enum CommandEnum {
    SIGNIN(new SignInCommand()),
    SIGNOUT(new SignOutCommand()),
    SIGNUP(new SignUpCommand()),
    CONFIRM_ORDER(new ConfirmOrderManagerCommand()),
    DENY_ORDER(new DenyOrderManagerCommand()),
    ACCEPT_ORDER(new AcceptOrderCommand()),
    COMPLETE_USER_ORDER(new CompleteUserOrderCommand()),
    EXTEND_USER_ORDER(new ExtendUserOrderCommand()),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    CHANGE_EMAIL(new ChangeEmailCommand()),
    MAKE_MANAGER(new MakeUserAManagerCommand()),
    MAKE_USER(new MakeManagerAnUserCommand()),
    RENT_CAR(new RentCarCommand());

    Command command;

    CommandEnum(final Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
