package by.training.info_system.resource.page;

import by.training.info_system.entity.role.Role;
import by.training.info_system.resource.ConfigurationManager;

import java.util.HashSet;
import java.util.Set;

public class ContactPage extends JspPage {
    ContactPage() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.UNAUTHORIZED_USER);
        roles.add(Role.USER);
        roles.add(Role.MANAGER);
        roles.add(Role.ADMIN);
        setUri("contact");
        setAllowedRoles(roles);
        setJspPagePath(ConfigurationManager.getInstance().getPagePath(getUri()));
    }
}
