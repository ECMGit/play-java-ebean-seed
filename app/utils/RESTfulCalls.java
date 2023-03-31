package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.typesafe.config.Config;
import play.Logger;
import play.api.Play;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @description: RESTful call functions
 * @author:  Beichen Hu
 * @create: 2023-03-30
 */
public class RESTfulCalls {
    /**
     * Response Type
     */
    public static enum ResponseType{
        SUCCESS, GENERALERROR, GETERROR, SAVEERROR, DELETEERROR, RESOLVEERROR, TIMEOUT, CONVERSIONERROR, UNKNOWN
    }

    /**
     * This method intends to create an internal response object in json node as response from RESTful call
     * @param type error type
     * @return error message in json
     */
    public static JsonNode createResponse(ResponseType type){
        ObjectNode jsonData = Json.newObject();
        switch (type){
            case SUCCESS:
                jsonData.put("success", "Success!");
                break;
            case GETERROR:
                jsonData.put("error", "Cannot get data from server!");
                break;
            case GENERALERROR:
                jsonData.put("error", "Generate error!");
                break;
            case SAVEERROR:
                jsonData.put("error", "Cannot be saved. The data must be invalid!");
                break;
            case RESOLVEERROR:
                jsonData.put("error", "Cannot be resolved on server!");
                break;
            case TIMEOUT:
                jsonData.put("error", "No response/Timeout from server!");
                break;
            case CONVERSIONERROR:
                jsonData.put("error", "Conversion error!");
                break;
            case DELETEERROR:
                jsonData.put("error", "Cannot be deleted on server");
            default:
                jsonData.put("error", "Unknown error!");
                break;
        }
        return jsonData;
    }
}
