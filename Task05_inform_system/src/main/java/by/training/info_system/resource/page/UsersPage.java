package by.training.info_system.resource.page;

import by.training.info_system.entity.role.Role;
import by.training.info_system.resource.ConfigurationManager;

import java.util.HashSet;
import java.util.Set;

public class UsersPage extends JspPage {
    UsersPage() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);
        setUri("users");
        setAllowedRoles(roles);
        setJspPagePath(ConfigurationManager.getInstance().getPagePath(getUri()));
    }
}
