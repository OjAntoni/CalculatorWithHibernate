package servlet;

import entity.Operation;
import entity.User;
import service.OperationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HistoryServlet", urlPatterns = "/history")
public class HistoryServlet extends HttpServlet {

    OperationService opService = new OperationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Operation> operations = opService.getAll(user);
        req.setAttribute("operations", operations);
        getServletContext().getRequestDispatcher("/history.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long operationId = Long.parseLong(req.getParameter("operation"));
        opService.delete(operationId);
        List<Operation> operations = opService.getAll(((User) req.getSession().getAttribute("user")));
        req.removeAttribute("operations");
        req.setAttribute("operations", operations);
        getServletContext().getRequestDispatcher("/history.jsp").forward(req,resp);
    }
}
