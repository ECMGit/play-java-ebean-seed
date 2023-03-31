package utils;
import java.util.regex.Pattern;

/**
 * @description: Constants store all basic server and API urls
 * @author: Beichen Hu
 * @create: 2023-03-30
 */
public class Constants {
    /*************************************** HOST ****************************************/
    public static final String URL_LOCAL = "http://localhost";
    public static final String URL_HOST = "http://localhost";
    /*************************************** HOST ****************************************/

    /*************************************** PORTS ***************************************/
    public static final String SMU_BACKEND_PORT = ":9005";
    public static final String SMU_FRONTEND_PORT = ":9001";
    /*************************************** PORTS ***************************************/

    /*************************************** USERS ***************************************/
    public static final String USER_LOGIN = "/user/login";
    public static final String USER_REGISTER_POST = "/user/signup";
    public static final String USER_UPDATE_POST = "/user/updateUser";
    public static final String USER_DETAIL_BY_ID = "/user/userDetailByID";
    /*************************************** USERS ***************************************/
}
