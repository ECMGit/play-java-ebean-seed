package models;

import io.ebean.Model;
import io.ebean.Finder;
import com.fasterxml.jackson.databind.JsonNode;
import play.Logger;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;


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

    public static final Finder<Long, User> find = new Finder<>(User.class);


    public static User findByName(String name) {
        return User.find.query()
                .where()
                .eq("username", name)
                .findOne();
    }
    /**
     * This method intends to deserialize a json and prepare a User object.
     * @param json given json
     */
    public void deserializeFromJson(JsonNode json) throws Exception {

        try {
            if (json.path("firstName") != null) this.firstName = json.path("firstName").asText();
            if (json.path("lastName") != null) this.lastName = json.path("lastName").asText();
            if (json.path("username") != null) this.username = json.path("username").asText();

        } catch (Exception e) {
            Logger.debug("User.deserializeFromJson exception: " + e);
            throw e;
        }
    }

    /**
     * This method receives a json and updates user's information based on the given json, and then call eBean save
     * function to store into database.
     * @param json given json of updated user's information
     */
    public void updateFromJson(JsonNode json) throws Exception {
        try {
            this.deserializeFromJson(json);
            this.save();
        } catch (Exception e) {
            Logger.debug("User.updateFromJson exception: " + e);
            throw e;
        }
    }


}
