package business;

import model.Team;
import model.TeamMember;
import model.User;
import util.DBName;
import utility.Database;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class TeamService {

    Database<User> dbUser = Database.getInstance();
    Database<Team> dbTeam = Database.getInstance();
    Database<TeamMember> dbTeamMember = Database.getInstance();
    List<TeamMember> teamMemberList = dbTeamMember.getValue(DBName.TEAM_MEMBER);
    List<Team> teamList = dbTeam.getValue(DBName.TEAM);
    List<User> users = dbUser.getValue(DBName.USER);

    //random number for id


    // FINDS TEAM BY ID
    public Team findById(int teamId) {
        Optional<Team> team = teamList.stream().filter(team1 -> team1.getTeamId() == teamId).findAny();
        return team.orElse(null);
    }

    //ADDS USER TO TEAM
    public String addUserToTeam(int userId, int teamId) {
        String response;
        //if both user and Team parameters are found add them in to database
        Optional<User> foundUser = users.stream().filter(user1 -> user1.getUserID() == userId).findAny();
        Optional<Team> foundTeam = teamList.stream().filter(team1 -> team1.getTeamId() == teamId).findAny();
        if (foundUser.isPresent() && foundTeam.isPresent()) {
            dbTeamMember.setValue(DBName.TEAM_MEMBER, new TeamMember(foundTeam.get(), foundUser.get()));
            //response sucess
            response = foundTeam.get().getName() + foundUser.get().getFirstName();
        } else {

            //failure response
            return "user or team is not found!";
        }
        return response;
    }

    public List<Team> teams() {
        return teamList;
    }


    //CREATS TEAM
    public void creatTeam(int id, String name, int userId) {
        Optional<User> CurrUser = users.stream().filter(user1 -> user1.getUserID() == userId).findAny();
        if (CurrUser.isPresent()) {
            User user = CurrUser.get();
            dbTeam.setValue(DBName.TEAM, new Team(id, name, user));
        }
    }

    //CREATS TEAM for overload
    public void creatTeam(String name, User user) {
        dbTeam.setValue(DBName.TEAM, new Team(getRandomId(), name, user));
    }


    public int getRandomId() {
        Random random = new Random();
        return random.nextInt(1000);
    }
}
    //RETURNS LIST OF USERS FROM TEAMMEMBERS DATABSE

//    public List<User> getTeamUsers(int teamId){
//        List<User> usersList =
//       return null;
//    }







