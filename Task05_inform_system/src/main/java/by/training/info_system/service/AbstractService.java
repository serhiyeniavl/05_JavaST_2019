package by.training.info_system.service;

import by.training.info_system.dao.DaoCreator;

public abstract class AbstractService implements Service {
    protected DaoCreator daoCreator;

    public void setDaoCreator(final DaoCreator daoCreator) {
        this.daoCreator = daoCreator;
    }
}
