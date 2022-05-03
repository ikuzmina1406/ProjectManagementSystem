package ua.GoIt.model;


import ua.GoIt.dao.Identity;

public class Skills implements Identity {
    private Long id;
    private String branch;
    private String level;


    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Skills{" +
                "id=" + id +
                ", branch='" + branch + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
