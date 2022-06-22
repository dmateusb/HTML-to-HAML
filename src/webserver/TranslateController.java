import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class TranslateController{

    public static String post(Request req, Response res){
        String body = req.body();
        JSONObject body_json = new JSONObject(body);
        String original_text = (String) body_json.get("text");

        return "";

    }
}