package controller;

import business.TaskService;
import com.google.gson.Gson;
import model.Task;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/tasks")
public class TaskController extends HttpServlet {
    private TaskService taskService=  new TaskService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        taskService.sendTaskList(resp,taskService.findByUserAndTeam(user));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user= (User) session.getAttribute("user");
        Task task = taskService.getTaskFromRequest(req,user);
        taskService.save(task);
        taskService.sendTaskList(resp,taskService.findByUserAndTeam(user));
    }
}
