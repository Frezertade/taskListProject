package business;

import model.Team;
import model.TeamMember;
import model.User;
import util.DBName;
import utility.Database;

import java.util.List;
import java.util.stream.Collectors;

public class TeamMemberSetvice {

    private Database<TeamMember> database;

    public TeamMemberSetvice(){
        database= Database.getInstance();
    }

    public List<TeamMember> findAll(){
       return database.getValue(DBName.TEAM_MEMBER);
    }
    public List<TeamMember> findByTeam(Team team){
        return findAll().stream()
                .filter(teamMember -> teamMember.getTeamId().getTeamId() == team.getTeamId())
                .collect(Collectors.toList());
    }

    public List<TeamMember> findByUser(User user){
        return findAll().stream()
                .filter(teamMember -> teamMember.getUserId().getUserID()== user.getUserID())
                .collect(Collectors.toList());
    }
}
