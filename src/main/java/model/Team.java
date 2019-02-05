package model;

/*Team model for Teams
 */



import model.User;
public class Team {
    private int teamId;
    private String name;
    private User createdBy;


    public Team(int teamId, String name, User createdBy) {
        this.teamId = teamId;
        this.name = name;
        this.createdBy = createdBy;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public int getTeamId() {
        return teamId;
    }

    public String getName() {
        return name;
    }

    public User getCreatedBy() {
        return createdBy;
    }
}
