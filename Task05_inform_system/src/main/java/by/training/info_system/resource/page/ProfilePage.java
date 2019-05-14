package by.training.info_system.resource.page;

import by.training.info_system.entity.role.Role;
import by.training.info_system.resource.ConfigurationManager;

import java.util.HashSet;
import java.util.Set;

class ProfilePage extends JspPage {
    ProfilePage() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        roles.add(Role.MANAGER);
        setUri("profile");
        setAllowedRoles(roles);
        setJspPagePath(ConfigurationManager.getInstance().getPagePath(getUri()));
    }
}
