package controller;

import model.*;
import utility.Database;
import util.DBName;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import business.TeamService;

import com.google.gson.Gson;

@WebServlet(urlPatterns = "/teamsById")
public class TeamByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        TeamService teamService = new TeamService();

        //get team id from user
        int teamId = Integer.parseInt(request.getParameter("teamId"));
       // int teamNum = Integer.getInteger(request.getParameter("teamId"));
        Team team = teamService.findById(teamId);
        String JSONteam;
        JSONteam = new Gson().toJson(team);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.write(JSONteam);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
