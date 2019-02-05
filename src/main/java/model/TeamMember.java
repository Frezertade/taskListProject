package model;


public class TeamMember {


    private Team teamId;
    private User userId;

    public TeamMember(Team teamId, User userId) {
        this.teamId = teamId;
        this.userId = userId;
    }

    public Team getTeamId() {
        return teamId;
    }

    public void setTeamId(Team teamId) {
        this.teamId = teamId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
