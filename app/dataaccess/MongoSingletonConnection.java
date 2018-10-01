package dataaccess;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;


//@Singleton
public class MongoSingletonConnection {

    private static  MongoSingletonConnection instance ;
//    @Inject
    private  MongoDatabase mongoDatabase ;

    private MongoSingletonConnection() {
        System.out.println("Singlton class constructor.================================================================");
        String server = "localhost";
        int port = 27017;
        // get mongodb connection
        MongoClient client = new MongoClient(server, port);
        mongoDatabase = client.getDatabase("Twitter");



    }

    public static MongoSingletonConnection getInstance() {
        System.out.println("--------------ana f get instance------------------s");
        if (instance == null)
            instance = new MongoSingletonConnection();
        else{
            System.out.println("ALREADY CREATED");
        }
        return instance;
    }


    public  MongoDatabase getMongoDatabase(){
        return this.mongoDatabase;
    }

//    public MongoClient createConnection(){
//        System.out.println("Singlton classsssssssssssssssssssssss");
//
//            String server = "localhost";
//            int port = 27017;
//            // get mongodb connection
//            mongoClient = new MongoClient(server, port);
////            MongoDatabase myDB = mongoClient.getDatabase("Twitter");
//            return mongoClient;
//            //Specify collection name
////            MongoCollection<Document> collection = myDB.getCollection("user");
//
//
//
//    }
//


}
