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
    List<Team> teamList= dbTeam.getValue(DBName.TEAM);
    List<User> userList= dbUser.getValue(DBName.USER);
    public Team findById(int teamId){


        Optional<Team> team = teamList.stream().filter(team1 -> team1.getTeamId() == teamId).findAny();
        return team.orElse(null);
    }

    //adds users to teams
    public void addUserToTeam(int teamId, int userId){
        Optional<User> user = userList.stream().filter(user1 -> user1.getUserID()==userId).findFirst();
        Optional<Team> team  = teamList.stream().filter(team1 -> team1.getTeamId() == teamId).findFirst();

        dbTeamMember.setValue(DBName.TEAM_MEMBER,new TeamMember(team.get(),user.get()));
    }

}
