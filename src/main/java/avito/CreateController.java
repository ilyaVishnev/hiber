package avito;

import DAO.MechanicDAO;
import cars_annot.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.ReplicationMode;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class CreateController extends HttpServlet {

    Logger logger = Logger.getLogger(CreateController.class.getName());
    MechanicDAO mechanicDAO = MechanicDAO.getMechanicDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/createCar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EngineA engineA = mechanicDAO.func(session -> {
            return session.get(EngineA.class,Integer.parseInt(req.getParameter("engine")));
        });
        GearboxA gearboxA = mechanicDAO.func(session -> {
            return session.get(GearboxA.class,Integer.parseInt(req.getParameter("gearbox")));
        });
        CarBodyA carBodyA = mechanicDAO.func(session -> {
            return session.get(CarBodyA.class,Integer.parseInt(req.getParameter("carbody")));
        });
        Holder holder = (Holder) req.getSession().getAttribute("user");
        CarA car = new CarA();
        car.setPrice(Integer.parseInt(req.getParameter("price")));
        car.setEngineA(engineA);
        car.setHolder(holder);
        car.setCarBodyA(carBodyA);
        car.setGearboxA(gearboxA);
        car.setDescription(req.getParameter("desc"));
        car.setStatus(Boolean.parseBoolean(req.getParameter("status")));
        car.setYear(Integer.parseInt(req.getParameter("year")));
        car.setPhoto(req.getParameter("myimage"));
        mechanicDAO.func(session -> {
            session.saveOrUpdate(car);
            return car;
        });
        doGet(req, resp);
    }
}
