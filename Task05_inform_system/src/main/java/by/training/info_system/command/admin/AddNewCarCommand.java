package by.training.info_system.command.admin;

import by.training.info_system.command.Command;
import by.training.info_system.command.client.RequestAttribute;
import by.training.info_system.command.client.RequestParameter;
import by.training.info_system.entity.Car;
import by.training.info_system.entity.data.CarInfo;
import by.training.info_system.resource.message.RequestMessage;
import by.training.info_system.resource.page.JspPage;
import by.training.info_system.resource.page.PageEnum;
import by.training.info_system.resource.page.PageFactory;
import by.training.info_system.service.CarService;
import by.training.info_system.validator.CarValidator;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

@Log4j2
public class AddNewCarCommand extends Command {
        @Override
    public JspPage execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        JspPage page = PageFactory.defineAndGet(PageEnum.CARS);

        Short yearMade = Short.parseShort(request.getParameter("year"));
        Short rentPrice = Short.parseShort(request.getParameter("rent"));
        String description = request.getParameter("description");
        String brandName = request.getParameter("brand_name");
        Character classAuto
                = request.getParameter("class").charAt(0);
        String vinCode = request.getParameter("vin");
        String regNumber = request.getParameter("reg");
        Integer run = Integer.parseInt(request.getParameter("run"));
        Car car = Car.builder()
                .yearMade(yearMade)
                .rentPrice(rentPrice)
                .description(description)
                .brandName(brandName)
                .carClass(classAuto)
                .carInfo(
                        CarInfo.builder()
                                .vinCode(vinCode)
                                .regNumber(regNumber)
                                .run(run)
                                .build()
                )
                .build();
        boolean isFileReceived = false;
        Integer isCarCreated = 0;
        try {
            Part part = request.getPart("photo");
            InputStream fileContent = part.getInputStream();
            String photoName = Paths.get(part.getSubmittedFileName())
                    .getFileName()
                    .toString();
            String descPath = request.getServletContext().getRealPath("");
            String[] dirs = descPath.split("/");
            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < dirs.length - 2; i++) {
                buffer.append(dirs[i]);
                buffer.append("/");
            }
            String pathToStorePhoto = buffer + "src/main/webapp/img/cars/"
                    + photoName;
            OutputStream outputStream = new FileOutputStream(pathToStorePhoto);
            fileContent.transferTo(outputStream);
            isFileReceived = true;
            car.setImagePath(photoName);
            CarService service = factory.getService(CarService.class)
                    .orElseThrow();
            isCarCreated = service.addCar(car);
        } catch (IOException e) {
            log.error("File path is nor correct", e);
        } catch (ServletException e) {
            log.error("Servlet exception: ", e);
        }

        appendTimeParam(page);
        if (validate(new CarValidator(), car) && isFileReceived
                && isCarCreated == 1) {
            appendRequestParameter(page, RequestParameter.ATTRIBUTE,
                    RequestAttribute.INFO.getValue());
            appendRequestParameter(page, RequestParameter.MESSAGE,
                    RequestMessage.CAR_ADDED);
        } else {
            appendRequestParameter(page, RequestParameter.ATTRIBUTE,
                    RequestAttribute.INCORRECT_DATA.getValue());
            appendRequestParameter(page, RequestParameter.MESSAGE,
                    RequestMessage.INCORRECT_CAR_ADD_FORM);
        }
        return page;
    }
}
