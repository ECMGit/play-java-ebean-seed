package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.User;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http;
import utils.Common;

import java.util.HashMap;
import java.util.Map;


/**
 * @description: user reg/login functions
 * @author: Junhao Shen, Beichen Hu
 * @create: 2023-03-30
 */
public class UserController extends Controller {

    /**
     * Send username and password, search user in database and compare password.
     * If it matches, then return good user info or return 'false'.
     *
     * @param request get request from frontend
     * @return User Json node if success, or 'false'
     */
    public Result authenticate(Http.Request request) {
        Logger.info("In authenticate. ");
        JsonNode req = request.body().asJson();
        // get username from frontend
        String username = req.get("username").asText();
        // get user password from frontend
        String password = req.get("password").asText();
        try {
            User user = User.findByName(username); // ( match where username and password both match )
            if(user!=null && username.equals(user.username) && password.equals(user.password)){
                // translate from User object to Json object then return to frontend
                JsonNode response = Json.toJson(user);
                return ok(response.toString());
            }else{
                return Common.badRequestWrapper("Authenticate failed. ");
            }
        }
        catch (Exception e) {
            return Common.badRequestWrapper("Authenticate failed. ");
        }

    }


    /**
     * When a user register, check if the username is taken
     * save to database if valid
     * POST
     * @return success if valid, fail if already taken
     */
    public Result registerNew(Http.Request request) {
        Logger.info("In register. ");
        JsonNode req = request.body().asJson();
        String username = req.get("username").asText();
        // get user info by 'username'
        User u = User.findByName(username);
        if (u == null) {
            // Parse User from Json to User object (func: Json.fromJson()).
            User user = Json.fromJson(req, User.class);
            user.save();
            ObjectNode result = Json.newObject();
            result.put("body", username);
            return ok(result);
        }else{
            return Common.badRequestWrapper("User Exist.");
        }
    }

    /**
     * This method receives updated information about user and saves the changes
     *
     * @return ok or bad request
     */
    public Result updateUser(Http.Request request) {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return Common.badRequestWrapper("Update failed");
        }
        try {
            Logger.info("In Update. ");
            String username = json.get("username").asText();
            User user = User.findByName(username);
            // if user found, then make the update.
            if (user != null) {
                user = Json.fromJson(json, User.class);
                // user.updateFromJson(json);
                user.update();
                return ok(Json.toJson(user).toString());
            } else {
                return Common.badRequestWrapper("Update failed");
            }
        } catch (Exception e) {
            Logger.debug("UserController.updateUser exception: " + e.toString());
            return Common.badRequestWrapper("Update failed");
        }
    }

    public Result userDetailByID(Long userId){
        try{
            if(userId == null) {
                Logger.debug("User id is null or empty for UserController.userDetailByID");
                return Common.badRequestWrapper("User is not valid.");
            }
            else{
                User curUser = User.findByID(userId);
                if( curUser == null){
                    Logger.debug("User not found in Database: " + userId);
                    return Common.badRequestWrapper("User not found with User id: " + userId);
                }
                JsonNode jsonUserData = Json.toJson(curUser);
                return ok(jsonUserData.toString());
            }
        } catch(Exception e){
            e.printStackTrace();
            Logger.debug("UserController.userDetailByID exception: " + e.toString());
            return Common.badRequestWrapper("User was not found.");
        }
    }
}
