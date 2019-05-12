package by.training.info_system.command.manager;

import by.training.info_system.command.Command;
import by.training.info_system.resource.page.JspPage;
import by.training.info_system.resource.page.PageEnum;
import by.training.info_system.resource.page.PageFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExtendUserOrderCommand extends Command {
    @Override
    public JspPage execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        System.out.println("extend");
        return PageFactory.defineAndGet(PageEnum.ORDERS);
    }
}
