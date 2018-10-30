package avito;

import DAO.MechanicDAO;
import cars_annot.Brand;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.hibernate.query.Query;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class FilterForList extends HttpServlet {

    private final MechanicDAO mechanicDAO = MechanicDAO.getMechanicDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject send = new JSONObject();
        JSONArray array = new JSONArray();
        Iterator<Brand> iterator = mechanicDAO.func(session -> {
            Query query = session.createQuery("from Brand ");
            return query.list();
        }).iterator();
        while (iterator.hasNext()) {
            JSONObject jsonObject = new JSONObject();
            Brand brand = iterator.next();
            jsonObject.put("id", brand.getId());
            jsonObject.put("brand", brand.getName());
            array.add(jsonObject);
        }
        send.put("idBrand", mechanicDAO.getArray()[0]);
        send.put("havePhoto", mechanicDAO.getArray()[1]);
        send.put("today", mechanicDAO.getArray()[2]);
        send.put("array", array);
        resp.getWriter().println(send);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        mechanicDAO.getArray()[0] = req.getParameter("brands");
        mechanicDAO.getArray()[1] = req.getParameter("photo");
        mechanicDAO.getArray()[2] = req.getParameter("today");
        req.getRequestDispatcher("/WEB-INF/list.jsp").forward(req, resp);
    }
}
