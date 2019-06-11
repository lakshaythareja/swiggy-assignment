package Swiggy.Utility;

import org.json.JSONException;
import org.json.JSONObject;

public class UtilityFunctions {

    public static JSONObject createMessage(int errorCode, String message){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("code",errorCode);
            jsonObject.put("message",message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
