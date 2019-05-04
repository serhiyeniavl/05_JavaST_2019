package by.training.info_system.resource.page;

import by.training.info_system.entity.role.Role;
import by.training.info_system.resource.ConfigurationManager;

import java.util.HashSet;
import java.util.Set;

public class SignUpPage extends JspPage {
    SignUpPage() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.UNAUTHORIZED_USER);
        setUri("signup");
        setAllowedRoles(roles);
        setJspPagePath(ConfigurationManager.getInstance().getPagePath(getUri()));
    }
}
