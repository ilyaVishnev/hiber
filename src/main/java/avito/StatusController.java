package avito;

import DAO.MechanicDAO;
import cars_annot.CarA;
import org.hibernate.ReplicationMode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StatusController extends HttpServlet {

    final MechanicDAO mechanicDAO = MechanicDAO.getMechanicDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarA car = mechanicDAO.func(session -> {
            return session.get(CarA.class, Integer.parseInt(req.getParameter(mechanicDAO.getArray()[0])));
        });
        req.setAttribute("desc", car.getDescription());
        req.setAttribute("carbody", car.getCarBodyA().getDescription());
        req.setAttribute("engine", car.getEngineA().getDescription());
        req.setAttribute("gearbox", car.getGearboxA().getDescription());
        req.setAttribute("photo", car.getPhoto());
        req.setAttribute("price", car.getPrice());
        req.setAttribute("status", car.getStatus());
        req.setAttribute("idHolder", car.getHolder().getId());
        req.setAttribute("id", car.getId());
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/description.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final CarA car = mechanicDAO.func(session -> {
            return session.get(CarA.class, Integer.parseInt(req.getParameter("id")));
        });
        car.setStatus(Boolean.parseBoolean(req.getParameter("status")));
        mechanicDAO.func(session -> {
            session.saveOrUpdate(car);
            return car;
        });
        mechanicDAO.parametrs(req.getParameter("id"));
        doGet(req, resp);
    }
}
