package com.example.domain;


public class Person implements Comparable<Person> {

    private String firstName;
    private String middleName;
    private String lastName;
    private String sex;
    private String date;
    private String hairColor;
    private String eyeColor;

    public int compareTo(Person o) {
        if (this == o) return 0;

        int result = this.lastName.compareTo(o.lastName);

        if (result == 0) {
            result = this.firstName.compareTo(o.firstName);

            if (result == 0) {
                result = this.middleName.compareTo(o.middleName);
            }
        }

        return result;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!firstName.equals(person.firstName)) return false;
        if (!middleName.equals(person.middleName)) return false;
        if (!lastName.equals(person.lastName)) return false;
        if (sex != person.sex) return false;
        if (!date.equals(person.date)) return false;
        if (!hairColor.equals(person.hairColor)) return false;
        return eyeColor.equals(person.eyeColor);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + middleName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + sex.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + hairColor.hashCode();
        result = 31 * result + eyeColor.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return
                "lastName='" + lastName + '\'' +
                        ", firstName='" + firstName + '\'' +
                        ", middleName='" + middleName + '\'' +
                        ", sex=" + sex +
                        ", data=" + date +
                        ", hairColor='" + hairColor + '\'' +
                        ", eyeColor='" + eyeColor + '\'';
    }
}
