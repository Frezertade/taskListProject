package model;

import util.Category;
import util.Priority;

public class TaskViewModel {

    private int id;
    private String task;
    private String dueDate;
    private Category category;
    private Priority priority;
    private String userName;
    private int userId;
    private String teamName;
    private int teamID;

    public TaskViewModel(int id, String task, String dueDate, Category category, Priority priority, String userName, int userId, String teamName, int teamID) {
        this.id = id;
        this.task = task;
        this.dueDate = dueDate;
        this.category = category;
        this.priority = priority;
        this.userName = userName;
        this.userId = userId;
        this.teamName = teamName;
        this.teamID = teamID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }
}
