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
                .filter(teamMember -> {
                    return ((teamMember.getTeamId() != null && team != null) && teamMember.getTeamId().getTeamId() == team.getTeamId()); // filter Team Member by team to get team Users
                })
                .collect(Collectors.toList());
    }

    public List<TeamMember> findByUser(User user){
        return findAll().stream()
                .filter(teamMember -> teamMember.getUserId().getUserID()== user.getUserID())
                .collect(Collectors.toList());
    }
}
