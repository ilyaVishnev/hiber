package todolist;

import DAO.ItemDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreateController extends HttpServlet {


    private ItemDAO itemDAO = ItemDAO.getItemDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONArray array = new JSONArray();
        JSONObject jsonSend = new JSONObject();
        Iterator<Item> itemIterator = itemDAO.func(session -> {
            final Query query = session.createQuery("from Item");
            return query.list();
        }).iterator();
        while (itemIterator.hasNext()) {
            Item item = itemIterator.next();
            JSONObject object = new JSONObject();
            object.put("id", item.getId());
            object.put("desc", item.getDesc());
            object.put("created", item.getCreated());
            object.put("done", item.isDone());
            array.put(object);
        }
        jsonSend.put("array", array);
        resp.getWriter().println(jsonSend);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        itemDAO.addItem(req.getParameter("desc"));
        resp.sendRedirect(String.format("%s/items", req.getContextPath()));
    }
}
