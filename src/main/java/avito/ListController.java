package avito;

import DAO.MechanicDAO;
import cars_annot.CarA;
import org.json.simple.*;
import org.json.JSONObject;

import org.hibernate.query.Query;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ListController extends HttpServlet {

    MechanicDAO mechanicDAO = MechanicDAO.getMechanicDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONArray jsonArray = new JSONArray();
        JSONObject send = new JSONObject();
        Iterator<CarA> iterator = mechanicDAO.func(session -> {
            final Query query = session.createQuery("from CarA");
            List<CarA> cars = query.list();
            return cars;
        }).iterator();
        while (iterator.hasNext()) {
            CarA car = iterator.next();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", car.getId());
            jsonObject.put("model", car.getGearboxA().getModel().toString());
            jsonObject.put("price", car.getPrice());
            jsonObject.put("photo", car.getPhoto());
            jsonObject.put("status", car.getStatus());
            jsonArray.add(jsonObject);
        }
        send.put("array", jsonArray);
        resp.getWriter().println(send);
    }
}
