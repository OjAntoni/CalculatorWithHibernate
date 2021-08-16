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

@WebServlet("/auth")
public class AuthorizationServlet extends HttpServlet {
    UserService service = new UserService();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init() throws ServletException {
        logger.info("auth servlet initialization");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("valid","true");
        getServletContext().getRequestDispatcher("/auth.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if(service.exists(username, password)){
            User byUsername = service.getByUsername(username);
            req.getSession().setAttribute("user", byUsername);
            getServletContext().getRequestDispatcher("/").forward(req, resp);
        }
        else {
            req.setAttribute("valid", "false");
            getServletContext().getRequestDispatcher("/auth.jsp").forward(req,resp);
        }
    }
}
