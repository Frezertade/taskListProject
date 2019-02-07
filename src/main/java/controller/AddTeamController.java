package controller;

import business.TeamService;
import model.Team;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import com.google.gson.*;
import java.util.List;

@WebServlet(urlPatterns = "/createTeam")
public class AddTeamController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        //get request parameters
        String teamName = request.getParameter("teamName");
        HttpSession session= request.getSession();
        User user = (User) session.getAttribute("user");
        TeamService service = new TeamService();
        service.creatTeam(teamName,user);
        List<Team> responseCame = service.teams();
        String JSONteamMemberList = new Gson().toJson(responseCame);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.write(JSONteamMemberList);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
