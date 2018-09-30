import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import controllers.DataTableObject;
import controllers.UsersController;
import models.User;
import models.UserCommandActor;
import models.UserQueryActor;
import org.bson.Document;
import org.junit.BeforeClass;
import org.junit.Test;
import play.mvc.Result;
import play.test.WithServer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static play.libs.Json.toJson;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;

public class ControllerTest{

    @Test
    public void testIndex() {
        Result result = new UsersController().index();
        assertEquals(OK, result.status());
        assertEquals("text/html", result.contentType().get());
        assertEquals("utf-8", result.charset().get());
        assertTrue(contentAsString(result).contains("Twitter"));
    }





}
