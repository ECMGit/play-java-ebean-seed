package models;

import io.ebean.Model;
import io.ebean.Finder;
import com.fasterxml.jackson.databind.JsonNode;

import play.Logger;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @description: user model
 * @author: Junhao Shen, Beichen Hu
 * @create: 2023-03-30
 */
@Entity
public class User extends Model {
    private static final long serialVersionUID = 1L;

    @Id
    public Long id;

    @Constraints.Required
    public String username;

    @Constraints.Required
    public String password;

    public String firstName;

    public String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static final Finder<Long, User> find = new Finder<>(User.class);


    /**
     * Find user by username
     * @param username username in database
     * @return
     */
    public static User findByName(String username) {
        return User.find.query()
                .where()
                .eq("username", username)
                .findOne();
    }

    /**
     * Find user by id
     * @param userID user id in database
     * @return
     */
    public static User findByID(Long userID) {
        return User.find.query()
                .where()
                .eq("id", userID)
                .findOne();
    }
}
