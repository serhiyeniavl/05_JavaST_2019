package by.training.info_system.resource.page;

import by.training.info_system.entity.role.Role;
import by.training.info_system.resource.ConfigurationManager;

import java.util.HashSet;
import java.util.Set;

class SignInPage extends JspPage {

    SignInPage() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.UNAUTHORIZED_USER);
        roles.add(Role.USER);
        roles.add(Role.MANAGER);
        roles.add(Role.ADMIN);
        setUri("signin");
        setAllowedRoles(roles);
        setJspPagePath(ConfigurationManager.getInstance().getPagePath(getUri()));
    }
}
