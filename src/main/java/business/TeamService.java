package business;

import model.Team;
import model.User;
import util.DBName;
import utility.Database;

import java.util.List;
import java.util.Optional;

public class TeamService {

    Database<User> dbUser = Database.getInstance();
    Database<Team> dbTeam = Database.getInstance();
    public Team findById(int teamId){

        List<Team> teamList= dbTeam.getValue(DBName.TEAM);
        Optional<Team> team = teamList.stream().filter(team1 -> team1.getTeamId() == teamId).findAny();
        return team.orElse(null);
    }

}
