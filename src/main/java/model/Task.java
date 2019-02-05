package model;

import util.Category;
import util.Priority;

public class Task {

    private int id;
    private String task;
    private String dueDate;
    private Category category;
    private Priority priority;
    private User createdBy;
    private Team team;

    public Task(int id, String task, String dueDate, Category category ,Priority priority ,User createdBy) {
        this.id = id;
        this.task = task;
        this.dueDate = dueDate;
        this.category = category;
        this.priority = priority;
        this.createdBy = createdBy;
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
