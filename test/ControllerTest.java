import com.mongodb.client.MongoDatabase;
import controllers.DataTableObject;
import controllers.UsersController;
import models.User;
import models.UserQueryActor;
import org.junit.Test;
import org.mockito.Mock;
import play.mvc.Http;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static play.mvc.Http.Status.*;
import static play.test.Helpers.*;

public class ControllerTest extends FunctioTest {
    @Mock private MongoDatabase database;

    @Test
    public void testIndex(){
        Result result = new UsersController().index();
//        Http.Response response = GET("/");
        assertEquals(OK, result.status());
        assertEquals("text/html", result.contentType().get());
        assertEquals("utf-8", result.charset().get());
        assertTrue(contentAsString(result).contains("Twitter"));
    }


    @Test
    public void testListAllUsers(){

        UsersController usersController = mock(UsersController.class);
        List<User> userList = new ArrayList<User>();
        DataTableObject dataTableObject = new DataTableObject();
        dataTableObject.setData(userList);
        given(usersController.ListAll()).willReturn(userList);

        mock.
        // Test Service
        UserService userService = new UserService(repositoryMock);
        User user = new User(1, "Johnny Utah");
        assertTrue(userService.isAdmin(user));
        verify(repositoryMock).findUserRoles(user);








    }
}
