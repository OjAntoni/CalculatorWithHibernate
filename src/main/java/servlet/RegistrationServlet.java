package servlet;

import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reg")
public class RegistrationServlet extends HttpServlet {

    UserService service = new UserService();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("some string");
        req.setAttribute("validUsername", true);
        getServletContext().getRequestDispatcher("/reg.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(service.exists(username)){
            req.setAttribute("validUsername", false);
            getServletContext().getRequestDispatcher("/reg.jsp").forward(req, resp);
        } else {
            service.add(new User(name, username, password));
            req.setAttribute("valid", "true");
            getServletContext().getRequestDispatcher("/auth.jsp").forward(req,resp);
        }
    }
}
