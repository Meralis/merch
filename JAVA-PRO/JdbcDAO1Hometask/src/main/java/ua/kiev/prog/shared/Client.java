package ua.kiev.prog.shared;

import java.util.Date;

public class Client {
    @Id
    private int id;

    private String name;
    private int age;

    private double number;

    private Date date1;

    public Client() {
    }

    public Client(String name, int age, double number, Date date1) {
        this.name = name;
        this.age = age;
        this.number = number;
        this.date1 = date1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", number=" + number +
                ", date= " + date1 +
                '}';
    }
}
