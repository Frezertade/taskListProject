package business;

import model.Team;
import model.TeamMember;
import model.User;
import util.DBName;
import utility.Database;
import java.util.List;
import java.util.Optional;

public class TeamService {

    Database<User> dbUser = Database.getInstance();
    Database<Team> dbTeam = Database.getInstance();
    Database<TeamMember> dbTeamMember = Database.getInstance();
    List<TeamMember> teamMemberList = dbTeamMember.getValue(DBName.TEAM_MEMBER);
    List<Team> teamList = dbTeam.getValue(DBName.TEAM);
    List<User> users = dbUser.getValue(DBName.USER);


    // FINDS TEAM BY ID
    public Team findById(int teamId) {
        Optional<Team> team = teamList.stream().filter(team1 -> team1.getTeamId() == teamId).findAny();
        return team.orElse(null);
    }

//ADDS USER TO TEAM
    public String addUserToTeam(int userId, int teamId) {
        String response;
        //if both user and Team parameters are found add them in to database
        dbUser.setValue(DBName.USER, new User(3, "FREZER", "TADESSE", "FREE@mum.edu", "432-234-2345", "fairfield,iowa,52557", "password"));
        dbTeam.setValue(DBName.TEAM, new Team(1, "PROFESSIONALS", users.get(0)));
        Optional<User> foundUser = users.stream().filter(user1 -> user1.getUserID() == userId).findAny();
        Optional<Team> foundTeam = teamList.stream().filter(team1 -> team1.getTeamId() == teamId).findAny();
        if (foundUser.isPresent() && foundTeam.isPresent()) {
            dbTeamMember.setValue(DBName.TEAM_MEMBER, new TeamMember(foundTeam.get(),foundUser.get()));
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


    //RETURNS LIST OF USERS FROM TEAMMEMBERS DATABSE

    public List<User> getTeamUsers(int id){
//        return new ArrayList<>() = teamMemberList.stream().filter(team -> team.getTeamId()==(id))
//                .map(teamMember -> teamMember.getUserId()).map(userid -> {users.stream().filter(user -> user.getUserID()==userid.getUserID()).findFirst().get();});
        return null;
    }

}


