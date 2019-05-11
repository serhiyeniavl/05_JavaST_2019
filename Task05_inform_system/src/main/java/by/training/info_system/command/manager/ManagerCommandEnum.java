package by.training.info_system.command.manager;

import by.training.info_system.command.Command;

public enum ManagerCommandEnum {
    CONFIRM(new ConfirmManagerCommand()),
    DENY(new DenyManagerCommand());

    private Command managerCommand;

    ManagerCommandEnum(Command managerCommand) {
        this.managerCommand = managerCommand;
    }

    public Command getManagerCommand() {
        return managerCommand;
    }
}
