package controller;

import com.google.gson.Gson;
import model.Team;
import model.TeamMember;
import model.User;
import util.DBName;
import utility.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;


@WebServlet(urlPatterns = "/addUserTeam")
public class TeamAddUserServlet extends HttpServlet {

    Database<User> dbUser = Database.getInstance();
    Database<Team> dbTeam = Database.getInstance();
    Database<TeamMember> dbTeamMemeber = Database.getInstance();
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            PrintWriter out = response.getWriter();

            //get request parameters
            int teamId = Integer.parseInt(request.getParameter("teamId"));
            int userId = Integer.parseInt(request.getParameter("userId"));

            //if both user and Team parameters are found add them in to database
            List<User> users = dbUser.getValue(DBName.USER);
            List<Team> teams = dbTeam.getValue(DBName.TEAM);
            Optional user = users.stream().filter(user1 -> user1.getUserID() == userId).findFirst();
            Optional team = teams.stream().filter(team1 -> team1.getTeamId() == teamId).findFirst();
            if (user.isPresent() && team.isPresent()) {
                dbTeamMemeber.setValue(DBName.TEAM_MEMBER, new TeamMember(dbTeam.getValue(DBName.TEAM).get(1), dbUser.getValue(DBName.USER).get(1)));

                String JSONteamMemberList;
                List<TeamMember> teamMemberList = dbTeamMemeber.getValue(DBName.TEAM_MEMBER);
                JSONteamMemberList = new Gson().toJson(teamMemberList);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.write(JSONteamMemberList);
            } else {
                String JSONteamNotFound = "{\"status\" :\" failure to add \",\"response\" : \"user or team dose not exist\"}";
                String Json = new Gson().toJson(JSONteamNotFound);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.write(Json);
            }
        }
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doPost(request,response);
        }
}
