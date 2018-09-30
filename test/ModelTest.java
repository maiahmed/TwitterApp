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
import play.mvc.Http;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static play.libs.Json.toJson;
import static play.mvc.Http.Status.OK;

public class ModelTest {

    private static MongoCollection<Document> collection;

    @BeforeClass
    public static void setup() {
        int recordsFiltered = 0;
        List<User> users = new ArrayList<User>();
        MongoDatabase myDB;


        //mongo DB
        String server = "localhost";
        int port = 27017;
        // get mongodb connection
        MongoClient client = new MongoClient(server, port);
        //specify DB name
        myDB = client.getDatabase("Twitter");
        //Specify collection name
        collection = myDB.getCollection("user");

        // Delete All documents from collection before using it
//        BasicDBObject document = new BasicDBObject();
        collection.deleteMany(new Document());


        // insert objects
        Document document = new Document();
        document.put("_id", 1);
        document.put("name", "mai");
        document.put("tweet", "hello Mai");
        collection.insertOne(document);

        document.put("name", "sara");
        document.put("tweet", "hello sara");
        document.put("_id", 2);
        collection.insertOne(document);

        document.put("_id", 3);
        document.put("name", "layla");
        document.put("tweet", "hello layla");
        collection.insertOne(document);

        document.put("_id", 4);
        document.put("name", "taghreed");
        document.put("tweet", "hello tagh");
        collection.insertOne(document);

        document.put("_id", 5);
        document.put("name", "soma");
        document.put("tweet", "hello soma");


        collection.insertOne(document);

    }


    @Test
    public void testListAllUsers() {

        List<User> users = new ArrayList<>();
        UserQueryActor userQueryActor = mock(UserQueryActor.class);

//        UsersController usersController1 ;
//
//        MongoCursor<Document> cursor = collection.find().limit(5).skip(1).iterator();
//
//        while (cursor.hasNext()) {
//
//            Document dbObject = cursor.next();
//
//            String id = dbObject.get("_id") + "";
//            System.out.println("ID : " + id);
//            String name = dbObject.get("name") + "";
//            String tweet = dbObject.get("tweet") + "";
//            User user = new User(Long.parseLong(id), name, tweet);
//            users.add(user);
//        }

        users.add(new User(1,"mai","it's mai"));
        users.add(new User(2,"layla","it's layla"));
        users.add(new User(3,"taghreed","it's tota"));
        users.add(new User(4,"sara","it's sara"));
        users.add(new User(5,"yara","it's yara"));
        users.add(new User(6,"heba","it's heba"));

        //Datatabel
        DataTableObject dataTableObject = new DataTableObject();
        dataTableObject.setDraw(1);
        dataTableObject.setRecordsTotal((int) collection.count());
        dataTableObject.setRecordsFiltered(5);
        dataTableObject.setSearch("");
        dataTableObject.setData(users);


        when(userQueryActor.listAllUsers("", 5, 0, 1)).thenReturn(dataTableObject);
        DataTableObject data = userQueryActor.listAllUsers("", 5, 0, 1);
//
        System.out.println("--------------> " + data.getData().get(2).name);
        assertEquals(data.getData().get(2).name, "taghreed");
        assertEquals(data.getData().get(2).name, "taghreed");

//        BasicDBObject query = new BasicDBObject();
//        query.put("name", "layla");
//        assertTrue(cursor.hasNext());
//        Document dbObject = (Document) cursor.next();
//        assertEquals("layla", dbObject.get("name"));


    }

    @Test
    public void testUpdate() {

        UserCommandActor commandActor = mock(UserCommandActor.class);
        User user = new User(1, "new name", "update");
        when(commandActor.updateUser(toJson(user), 1)).thenReturn(1);

        assertEquals(1, commandActor.updateUser(toJson(user), 1));
//        commandActor.updateUser(user, 1);


//        UpdateResult res = collection.updateOne(eq("_id", 4), new Document("$set", new Document("tweet", "hello taghreeeeed")));
//        BasicDBObject query = new BasicDBObject();
//        query.put("_id", 4);
//        MongoCursor cursor = collection.find(query).iterator();
//        assertTrue(cursor.hasNext());
//        Document dbObject = (Document) cursor.next();
//        assertEquals("hello taghreeeeed", dbObject.get("tweet"));
    }



    @Test
    public void create() {

        User user = new User(6, "new", "ana new");

        UserCommandActor userCommandActor = mock(UserCommandActor.class);

        User newUsr = new User();
        when(userCommandActor.addUser(user)).thenReturn(true);

        System.out.println(" CREATE _________________ " + userCommandActor.addUser(user));
//        Document document = new Document();
//
//        document.put("_id", 6);
//        document.put("name", "new");
//        document.put("tweet", "hello new");
//
//
//        collection.insertOne(document);
//        BasicDBObject query = new BasicDBObject();
//        query.put("name", "new");
//        MongoCursor cursor = collection.find(query).iterator();
//
//        assertTrue( cursor.hasNext());

    }
}
