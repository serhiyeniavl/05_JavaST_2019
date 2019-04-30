package by.training.info_system.resource.path;

import by.training.info_system.resource.ConfigurationManager;

public enum URIPath {
    HOME(ConfigurationManager.getInstance().getProperty("path.page.index")),
    CARS(ConfigurationManager.getInstance().getProperty("path.page.cars"));

    private String path;

    URIPath(final String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
