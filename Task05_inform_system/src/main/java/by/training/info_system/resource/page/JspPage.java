package by.training.info_system.resource.page;

import by.training.info_system.entity.role.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter(AccessLevel.PROTECTED)
@Getter
public abstract class JspPage {
    private Set<Role> allowedRoles;
    private String uri;
    private String jspPagePath;
    private String requestParameters = "";

    private boolean isRedirect;

    public void setRedirect(final boolean redirect) {
        isRedirect = redirect;
    }

    public void appendRequestParameter(final String parameter) {
        if (requestParameters.isEmpty()) {
            this.requestParameters = "?" + parameter;
        } else {
            this.requestParameters = requestParameters.concat("&" + parameter);
        }
    }

    void clearRequestParameters() {
        this.requestParameters = "";
    }
}
