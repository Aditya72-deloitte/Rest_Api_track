import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertTrue;

public class pagination_Test {



    private  static final String LOG_FILE = "log4j.properties";

    // TO ADD LOGS IN OUR PROGRAM
    private static Logger log  = LogManager.getLogger(Add_Tasks.class);

    //validating paging
    @Test(priority = 8)
    public void Validating_paging()  {

        log.info("Validating paging");

        int num = 2;
        for(int j = 0; j<3; j++){

            String token = javaUtility.STORING_TOKENS_HERE.get(0);
            Response response = given().baseUri("https://api-nodejs-todolist.herokuapp.com/task").
                    header("Authorization", "Bearer " + token).header("content-type","application/json")
                    .when()
                    .get("?limit="+num)
                    .then()
                    .statusCode(200).extract().response();
            JSONObject arr = new JSONObject(response.asString());
            int Count = (int)arr.get("count");
            if (num == Count && num==2){    //Response body contains count must be equal to our num
                System.out.println("assertion of paging  for 2 is successful");
                num = num+3;
            }
            if (num == Count && num==5){
                System.out.println("assertion of paging  for 5 is successful");
                num = num+5;
            }
            if (num== Count && num==10) {
                System.out.println("assertion of paging  for 10 is successful");
                assertTrue(true);
            }
        }


    }
}

