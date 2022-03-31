import org.testng.annotations.Test;
import java.io.File;
import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class RestAssuredtestng {

    @Test
    public void get_call(){
        given().
                baseUri("https://jsonplaceholder.typicode.com").
                header("content type","applicaion/json").
                when().
                get("/posts").
                then().body("id", hasItems(1,2),
                        "[39].id)", is(equalTo(4))).
                statusCode(200);
    }


    @Test
    public void put_call(){
        File jsonData= new File("src//test//resources//rest.json");
        given().
                baseUri("https://jsonplaceholder.typicode.com").
                body(jsonData).
                header("content type","application/json").
                when().
                put("/posts").
                then().
                statusCode(200);
    }


}