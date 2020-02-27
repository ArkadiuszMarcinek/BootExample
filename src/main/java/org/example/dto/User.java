package org.example.dto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

import org.example.enums.Sex;

public class User  {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name) &&
                Objects.equals(birthday, user.birthday) &&
                sex == user.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday, sex, age);
    }

    private final String name;
    private final LocalDate birthday;
    private Sex sex;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
        this.birthday = LocalDate.now();
    }

    public User(String name) {
        this.name = name;
        this.birthday = LocalDate.now();
    }

    public User(String name, Sex sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.birthday = LocalDate.now();
    }

    public User(String name, LocalDate birthday) {
        this.name = name;
        this.sex = Sex.U;
        this.age = 1;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getName() {
        return name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
