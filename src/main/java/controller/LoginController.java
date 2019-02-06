package controller;

import business.Login;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username= req.getParameter("username");
        String password= req.getParameter("password");
        RequestDispatcher errorDispatcher= req.getRequestDispatcher("login.jsp");
        RequestDispatcher successDispatcher = req.getRequestDispatcher("task.html");
        String errorMessage="";
        boolean error=false;
        if(username== null || password== null){
          errorMessage="username and password is required";
          error= true;
        }else if(!Login.login(username,password)){
            errorMessage= "Username or password is incorrect";
            error =true;
        }else {
            HttpSession session= req.getSession();
            User user= Login.findUserByEmail(username);
            if(user != null)session.setAttribute("user",user);
           successDispatcher.forward(req,resp);
        }
        if(error) {
            req.setAttribute("error",error);
            req.setAttribute("errorMessage",errorMessage);
            errorDispatcher.forward(req,resp);
        }

    }
}
