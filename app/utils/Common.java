package utils;

import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: Common tools
 * @author: Beichen Hu
 * @create: 2023-03-30
 */
public class Common {
    /**
     * Return bad or error response to frontend request.
     * @param errorInfo: send bad or error response to frontend
     * @return
     */
    public static Result badRequestWrapper(String errorInfo){
        Map<String, String> map = new HashMap<>();
        map.put("error", errorInfo);
        return Results.ok(Json.toJson(map).toString());
    }
}
