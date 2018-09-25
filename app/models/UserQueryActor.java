package models;


import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import controllers.DataTableObject;
import org.bson.Document;
import scala.util.parsing.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserQueryActor {



    public DataTableObject listAllUsers(String search, int length, int start, int draw ){

        int recordsFiltered = 0;
        List<User> users = new ArrayList<User>();

        //mongo DB
        String server = "localhost";
        int port = 27017;
        // get mongodb connection
        MongoClient client = new MongoClient(server, port);
        //specify DB name
        MongoDatabase myDB = client.getDatabase("Twitter");
        //Specify collection name
        MongoCollection<Document> collection = myDB.getCollection("user");


        MongoCursor<Document> cursor = null;
        //find and filter
        if(search.equals(null) || search.equals("") ){
            System.out.println("null");
            cursor = collection.find().limit(length).skip(start).iterator();
            recordsFiltered =  (int)collection.count();
        }
        else cursor = collection.find(Document.parse("{name: {$regex: \"" +search + "\"}}")).limit(length).skip(start).iterator();

        while(cursor.hasNext()){

            Document dbObject = cursor.next();

            String id =   dbObject.get("_id")+"";
            System.out.println("ID : " + id);
            String name = dbObject.get("name") + "";
            String tweet = dbObject.get("tweet") + "";
            User user = new User( Long.parseLong(id), name, tweet);
            users.add(user);
            recordsFiltered ++;
        }

        System.out.print(draw + " " + length + " " + start+ " " + " " + recordsFiltered + " " + search+" ,, "+ (int) collection.count());


        //Datatabel
        DataTableObject dataTableObject = new DataTableObject();
        dataTableObject.setDraw(draw++);
        dataTableObject.setRecordsTotal((int) collection.count());
        dataTableObject.setRecordsFiltered(recordsFiltered);
        dataTableObject.setSearch(search);
        dataTableObject.setData(users);

        return dataTableObject;

    }

    public User getUser(int id) {

        User user = new User();
        //mongo DB
        String server = "localhost";
        int port = 27017;
        // get mongodb connection
        MongoClient client = new MongoClient(server, port);
        //specify DB name
        MongoDatabase myDB = client.getDatabase("Twitter");
        //Specify collection name
        MongoCollection<Document> collection = myDB.getCollection("user");

//        MongoCursor<Document> cursor = null;
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);

        MongoCursor cursor = collection.find(query).iterator();

        JSONObject userNode = null;
        if(cursor.hasNext())
        {
            Document dbObject = (Document) cursor.next();

            String name = dbObject.get("name") + "";
            String tweet = dbObject.get("tweet") + "";
            System.out.println( " Data " + id + " " + name + " " + tweet);
//            user = new User(id, name, tweet);
            user.setId(id);
            user.setName(name);
            user.setTweet(tweet);
//            user.add(user);
            System.out.println(user + "    ,,,,  " + user.toString());

//            userNode = new JSONObject(user);

//            System.out.println(" JSON " + userNode);
        }


        return user;

    }
}
