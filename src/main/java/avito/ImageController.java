package avito;

import DAO.MechanicDAO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.*;
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

public class ImageController extends HttpServlet {

    final Logger logger = Logger.getLogger(ImageController.class.getName());
    String fileway;
    final MechanicDAO mechanicDAO = MechanicDAO.getMechanicDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject object = new JSONObject();
        fileway = "pictures/" + fileway.substring(fileway.lastIndexOf("\\") + 1);
        object.put("image", fileway);
        resp.getWriter().println(object);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File file;
        int maxFileSize = 5000 * 1024;
        int maxMemSize = 5000 * 1024;
        BufferedImage img;
        ServletContext servletContext = this.getServletContext();
        String filePath = servletContext.getInitParameter("file-upload");
        String contentType = req.getContentType();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(maxMemSize);
        factory.setRepository(new File("C:\\shop"));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(maxFileSize);
        try {
            List fileItems = upload.parseRequest(req);
            Iterator iterator = fileItems.iterator();
            while (iterator.hasNext()) {
                FileItem fileItem = (FileItem) iterator.next();
                String fileName = fileItem.getName();
                fileItem.write(new File(fileway = filePath +
                        fileName.substring(fileName.lastIndexOf("\\") + 1)));
            }
        } catch (Exception ex) {
            logger.info(ex.getMessage());
        }
    }
}
