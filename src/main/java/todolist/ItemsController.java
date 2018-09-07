package todolist;

import DAO.ItemDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

public class ItemsController extends HttpServlet {

    private ItemDAO itemDAO = ItemDAO.getItemDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/index.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] values = req.getParameter("done").toString().split(" ");
        Item item = itemDAO.getItemById(values[0]);
        if (values[1].equals("on")) {
            item.setDone(true);
        } else {
            item.setDone(false);
        }
        itemDAO.func(session -> {
            session.saveOrUpdate(item);
            return item;
        });
        doGet(req, resp);
    }
}
