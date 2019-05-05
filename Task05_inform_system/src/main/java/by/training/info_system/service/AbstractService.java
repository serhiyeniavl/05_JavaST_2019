package by.training.info_system.service;

import by.training.info_system.dao.DaoManager;

public abstract class AbstractService implements Service {
    protected DaoManager daoManager;

    public void setDaoManager(final DaoManager daoManager) {
        this.daoManager = daoManager;
    }
}
