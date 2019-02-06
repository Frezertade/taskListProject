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
        String username= req.getParameter("username"); // get the username form the input parameter
        String password= req.getParameter("password");// get the password form the input parameter

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
            HttpSession session= req.getSession(); // get the session if exit if don't exit create one
            User user= Login.findUserByEmail(username); // get the user by using email(username)
            if(user != null)session.setAttribute("user",user);
            req.getRequestDispatcher("tasks.html").forward(req,resp); // forward the request to the task html
        }
        if(error) {
            req.setAttribute("username",username);
            req.setAttribute("password",password);
            req.setAttribute("error",error); // set the error true to display the error message
            req.setAttribute("errorMessage",errorMessage); // set the error message that display in the error message
            req.getRequestDispatcher("indexBU.jsp").forward(req,resp); // return to the same page
        }

    }
}
