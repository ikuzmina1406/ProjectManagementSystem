package ua.GoIt.model;

import ua.GoIt.dao.Identity;

import java.math.BigDecimal;
import java.sql.Date;


public class Developers  implements Identity {
    private long id;
    private String name;
    private String first_name;
    private String last_name;
    private int age;
    private Date birthday;
    private String sex;
    private Long state_code;
    private String country;
    private String address;
    private int status;
    private BigDecimal salary;

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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public Long getState_code() {
        return state_code;
    }

    public void setState_code(Long state_code) {
        this.state_code = state_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Developers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", state_code=" + state_code +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", salary=" + salary +
                '}';
    }
}
