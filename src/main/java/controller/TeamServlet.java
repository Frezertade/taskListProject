package controller;

import com.google.gson.Gson;
import model.Team;
import model.User;
import utility.Database;
import util.DBName;
import business.TeamService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

    @WebServlet(urlPatterns = "/teams")
public class TeamServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        }
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            PrintWriter out = response.getWriter();

           //GET TEAM SERVICE AND RESPOND TEAMS FROM DATA
            TeamService teamServe = new TeamService();
            String JSONteams;
            List<Team> teamList = teamServe.teams();
            JSONteams = new Gson().toJson(teamList);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.write(JSONteams);
        }
}
