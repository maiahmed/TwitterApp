package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import controllers.DataTableObject;
import org.bson.Document;
import play.api.mvc.Request$;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Http;

import javax.inject.Inject;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.*;

public class UserCommandActor {

    @Inject
    FormFactory formFactory;

    public MongoCollection<Document> createDBConnection(){
        String server = "localhost";
        int port = 27017;
        MongoClient client = new MongoClient(server, port);
        MongoDatabase myDB = client.getDatabase("Twitter");
        MongoCollection<Document> collection = myDB.getCollection("user");
        return collection;

    }
    public void addUser(User user){

        MongoCollection<Document> collection = createDBConnection();
        Document document = new Document();


        System.out.println("USER " + user);
        document.put("name", user.getName());
        document.put("tweet", user.getTweet());
        document.put("_id", collection.count()+1);
        System.out.println("USER : " + user.getName() + " " + user.getTweet());


        collection.insertOne(document);

    }


    public void updateUser(JsonNode userJson, int id){

        System.out.println("UPDATEEEEEEEEEEEEEEEEEEEEEEEEEEEE " + userJson);
        MongoCollection<Document> collection = createDBConnection();

        User oldUser = new User();

//        int id = userJson.findPath("id").intValue();
        String name = String.valueOf(userJson.get(0).get("value").textValue());
        String tweet = String.valueOf(userJson.get(1).get("value").textValue());

        System.out.println(" --- ana b update "  +name + " " +tweet + " " + userJson.get(1).get("value"));


        MongoCursor<Document> cursor = null;
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);

       UserQueryActor userQueryActor = new UserQueryActor();
       oldUser = userQueryActor.getUser(id);
       User user = new User(id, name, tweet);

       System.out.println("OLD:   " + oldUser.name + " " + oldUser.id + " " + oldUser.tweet);
        System.out.println("NEW:  " + user.id + " " + user.name + " " + user.tweet);

        UpdateResult res = collection.updateOne(eq("_id", id), new Document("$set", new Document("tweet", user.getTweet())));
        System.out.println(res.getModifiedCount());

    }



}
