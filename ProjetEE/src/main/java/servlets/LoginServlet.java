package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session_null = req.getSession();
        session_null.invalidate();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(username == null || password == null){
            RequestDispatcher rd = req.getRequestDispatcher("index.html");
            rd.include(req,resp);
        }else{
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            RequestDispatcher rd = req.getRequestDispatcher("user");
            rd.forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }
}
