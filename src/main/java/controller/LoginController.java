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
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username= req.getParameter("username");
        String password= req.getParameter("password");
        RequestDispatcher errorDispatcher= req.getRequestDispatcher("indexBU.jsp");
        RequestDispatcher successDispatcher = req.getRequestDispatcher("tasks.html");
        String errorMessage="";
        boolean error=false;
        Login.addStaticUser();
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
            req.setAttribute("username",username);
            req.setAttribute("password",password);
            req.setAttribute("error",error);
            req.setAttribute("errorMessage",errorMessage);
            errorDispatcher.forward(req,resp);
        }

    }
}
