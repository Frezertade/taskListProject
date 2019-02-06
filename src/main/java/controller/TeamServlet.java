package controller;

import com.google.gson.Gson;
import model.Team;
import model.User;
import model.TeamMember;
import utility.Database;
import utility.MockData;
import util.DBName;

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

            Database<User> dbUser = Database.getInstance();
            Database<Team> dbTeam = Database.getInstance();


            dbUser.setValue(DBName.USER, new User(1,"FREZER","TADESSE","FREE@mum.edu","432-234-2345","fairfield,iowa,52557","password"));
            dbUser.setValue(DBName.USER, new User(2,"Gebere","MANOFTHEYEAR","GEREe@mum.edu","510-234-2345","fairfield,iowa,52557","password") );

            dbTeam.setValue(DBName.TEAM,new Team(1,"Programers",dbUser.getValue(DBName.USER).get(0)));
            dbTeam.setValue(DBName.TEAM,new Team(1,"Programers",dbUser.getValue(DBName.USER).get(1)));


            String JSONteams;
            List<Team> teamList = dbTeam.getValue(DBName.TEAM);
            JSONteams = new Gson().toJson(teamList);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.write(JSONteams);
        }

}
