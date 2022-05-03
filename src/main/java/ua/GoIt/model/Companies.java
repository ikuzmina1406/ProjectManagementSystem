package ua.GoIt.model;

import ua.GoIt.dao.Identity;

public class Companies implements Identity {
    private long id;
    private String name;
    private String state_code;
    private String country;
    private String info;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Companies{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state_code='" + state_code + '\'' +
                ", country='" + country + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
