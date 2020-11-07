import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

import static io.restassured.RestAssured.given;

public class BaseTestClass {
    @Test
    public void testDataPreparing(){
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(16);
        BasicConfigurator.configure();
        String json = given().baseUri("https://www.treasurydirect.gov/NP_WS")
                        .basePath("debt/search")
                        .when()
                        .formParam("startdate", "1993-01-01")
                        .formParam("enddate", "2020-10-19")
                        .formParam("format", "json").get()
                .then().extract().response().body().asString();
        System.out.println(json);
        JsonObject convertedObject = new Gson().fromJson(json, JsonObject.class);
        System.out.println(convertedObject.getAsJsonArray("entries").size());

    }
}
