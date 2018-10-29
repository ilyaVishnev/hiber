package avito;

import DAO.MechanicDAO;
import cars_annot.Holder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInController extends HttpServlet {

    final MechanicDAO mechanicDAO = MechanicDAO.getMechanicDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Holder holder = mechanicDAO.isCredential(req.getParameter("login"), req.getParameter("password"));
        if (holder == null) {
            req.setAttribute("error", "there isnt such user");
            doGet(req,resp);
        } else {
            req.getSession().setAttribute("user", holder);
            req.getRequestDispatcher("/WEB-INF/list.jsp").forward(req, resp);
        }
    }
}
