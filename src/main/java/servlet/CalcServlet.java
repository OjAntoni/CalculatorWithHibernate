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
import java.util.Date;

@WebServlet("/calc")
public class CalcServlet extends HttpServlet {

    OperationService opService = new OperationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/calc.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double num1 = Double.parseDouble(req.getParameter("num1"));
        double num2= Double.parseDouble(req.getParameter("num2"));
        String operation = req.getParameter("operation");
        User user = (User) req.getSession().getAttribute("user");
        double calculate = opService.calculate(num1, num2, operation);
        opService.add(new Operation(num1,num2,operation,calculate, new Date(),user));
        req.setAttribute("result", calculate);
        getServletContext().getRequestDispatcher("/calc.jsp").forward(req,resp);
    }
}
