package domain;

import java.util.Objects;

public class Client extends domain.Entity<Long> {

    private String firstName;
    private String secondName;
    private String job;
    private int age;

    public Client(String firstName, String secondName, String job, int age) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.job = job;
        this.age = age;
    }

    /**
     * Returns the first name as String.
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name. String required.
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name as String.
     * @return String
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     * Sets the last name. String required.
     * @param secondName
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     * Returns the job of client.
     * @return String
     */
    public String getJob() {
        return job;
    }

    /**
     * Sets the job for client. String required.
     * @param job
     */
    public void setJob(String job) {
        this.job = job;
    }


    /**
     * Returns the age of client.
     * @return Integer
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of client. Integer required.
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Checks equality between two Client entities based on reference or fields.
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return age == client.age &&
                firstName.equals(client.firstName) &&
                secondName.equals(client.secondName) &&
                Objects.equals(job, client.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, job, age);
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", job='" + job + '\'' +
                ", age=" + age +
                '}';
    }
}
