package by.training.info_system.resource.page;

import by.training.info_system.entity.role.Role;
import by.training.info_system.resource.ConfigurationManager;

import java.util.HashSet;
import java.util.Set;

class HomePage extends JspPage {

    HomePage() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.UNAUTHORIZED_USER);
        roles.add(Role.USER);
        roles.add(Role.MANAGER);
        roles.add(Role.ADMIN);
        setUri("home");
        setAllowedRoles(roles);
        setJspPagePath(ConfigurationManager.getInstance().getPagePath(getUri()));
    }

}
