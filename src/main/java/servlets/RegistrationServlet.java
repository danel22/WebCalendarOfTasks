package servlets;

import services.RegistrationService;
import services.RegistrationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
    private static RegistrationService rs=new RegistrationServiceImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login=req.getParameter("login");
        String password=req.getParameter("password");
        if(rs.regUser(login,password)){
            req.getSession().setAttribute("isAuth", true);
            req.getRequestDispatcher("/hello").forward(req, resp);
        }else{
            getServletContext().getRequestDispatcher("/registration.jsp").forward(req,resp);
        }

    }
}
