package by.training.info_system.resource.page;

import by.training.info_system.entity.role.Role;
import by.training.info_system.resource.ConfigurationManager;

import java.util.HashSet;
import java.util.Set;

class OrdersPage extends JspPage {
    OrdersPage() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.MANAGER);
        roles.add(Role.ADMIN);
        setUri("orders");
        setAllowedRoles(roles);
        setJspPagePath(ConfigurationManager.getInstance().getPagePath(getUri()));
    }
}
