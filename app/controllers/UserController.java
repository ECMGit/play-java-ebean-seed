package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.User;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http;

import java.util.HashMap;
import java.util.Map;


/**
 * @description: user reg/login
 * @author: Junhao Shen
 * @create: 2023-03-09
 */

public class UserController extends Controller {

    public Result authenticate(Http.Request request) {

        System.out.println("In authenticate");
        JsonNode req = request.body().asJson();
        String username = req.get("username").asText();
        String password = req.get("password").asText();

        try {
            User user = User.findByName(username); // ( match where username and password both match )
            if(user!=null && username.equals(user.username) && password.equals(user.password)){
                JsonNode response = Json.toJson(user);
                return ok(response.toString());
            }else{
                return ok("false");
            }
        }
        catch (Exception e) {
            return ok("false");
        }

    }


    /**
     * When a user register, check if the username is taken
     * save to database if valid
     * POST
     * @return success if valid, fail if already taken
     */

    public Result registerNew(Http.Request request) {
        System.out.println("In register");
        JsonNode req = request.body().asJson();
        String username = req.get("username").asText();
        String password = req.get("password").asText(); //how to make it more secure?
        String firstName =req.get("firstName").asText();
        String lastName = req.get("lastName").asText();

        User u = User.findByName(username);
        ObjectNode result = null;
        if (u == null) {
            System.out.println("new user");
            User user = new User();
            user.username=username;
            user.password=password;
            user.firstName=firstName;
            user.lastName=lastName;
            user.save();
            result = Json.newObject();
            result.put("body", username);
        }
        return ok(result);
    }

    /**
     * This method receives updated information about user and saves the changes
     *
     * @return ok or bad request
     */
    public Result updateUser(Http.Request request) {
        JsonNode json = request.body().asJson();
        if (json == null) {

            return ok("false");
        }

        try {



            System.out.println("In Update User: "+ json.toString());
            String username = json.get("username").asText();
            User user = User.findByName(username);
            // if user found, then make the update.
            if (user != null) {
                user.updateFromJson(json);
                return ok(Json.toJson(user).toString());
            } else {
                return ok("false");
            }
        } catch (Exception e) {
            Logger.debug("UserController.updateUser exception: " + e.toString());
            return ok("false");
        }
    }

}
