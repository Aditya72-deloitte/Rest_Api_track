import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class RestAssuredtest {

    @BeforeSuite
    public void nothing() {
        RestAssured.useRelaxedHTTPSValidation();
    }
    @Test
    public void get_call(){
        given().
                baseUri("https://jsonplaceholder.typicode.com").
                header("Content-Type","applicaion/json").
                when().
                get("/posts").
                then().
                statusCode(200);
    }

    @Test
    public void get_verify(){

        Response response= given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then().extract().response();
        JSONArray arr= new JSONArray(response.asString());
        Object id= arr.getJSONObject(39).get("id");
        Object userId= arr.getJSONObject(39).get("userId");
        System.out.println("User with "+id+"have userId= "+userId);
        for(int i =0; i <arr.length();i++){
            Object obj= arr.getJSONObject(i).get("title");
            System.out.println(obj.getClass().getName());
        }
    }

    @Test
    public void put_call(){
        File jsonData= new File("C:\\Users\\adityakumar72\\IdeaProjects\\RestApiTest\\src\\test\\resources\\asset.json");
        given()
                .baseUri("https://reqres.in/api").
                body(jsonData)
                .header("Content-Type","application/json")
                .when()
                .put("/users")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("name", equalTo("Arun"))
                .body("job",equalTo("Manager"));
    }
}