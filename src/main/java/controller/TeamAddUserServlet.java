package controller;

import com.google.gson.Gson;
import business.TeamService;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;



@WebServlet(urlPatterns = "/addUserTeam")
public class TeamAddUserServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            PrintWriter out = response.getWriter();
            //get request parameters
            int teamId = Integer.parseInt(request.getParameter("teamId"));
            HttpSession session= request.getSession();
            User user = (User) session.getAttribute("user");
            TeamService service = new TeamService();
            String JSONteamMemberList;
              String responseCame  = service.addUserToTeam(teamId,user.getUserID());
                JSONteamMemberList = new Gson().toJson(responseCame );
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.write(JSONteamMemberList);

        }
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doPost(request,response);
        }
}
