package controllers;
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientURI;
//import com.mongodb.client.MongoCollection;

import com.fasterxml.jackson.databind.JsonNode;
import models.User;
import models.UserCommandActor;
import models.UserQueryActor;
import play.data.FormFactory;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

import static play.libs.Json.toJson;

//import org.bson.Document;

public class UsersController extends Controller {

    @Inject
    FormFactory formFactory;
    // print All

    DataTableObject table;

    public Result ListAll() {

        System.out.println("ana f list aLllllllllllll y gma3a");

//        System.out.println(request());

        //get request parameters
        int draw = Integer.parseInt(request().getQueryString("draw"));
        int length = Integer.parseInt(request().getQueryString("length"));
        int start = Integer.parseInt(request().getQueryString("start"));
        String search = String.valueOf(request().getQueryString("search[value]"));

        UserQueryActor userQueryActor = new UserQueryActor();
        table = userQueryActor.listAllUsers(search, length, start, draw);
        return ok(toJson(table));

    }

    public Result index() {
        return ok(views.html.index.render());

    }


    //get one user by id

    public Result getUser(int id) {


        UserQueryActor userQueryActor = new UserQueryActor();
        User user = userQueryActor.getUser(id);
        System.out.println("ana f get One  " + user);
        return ok(toJson(user));

    }

    /// create user
    public Result save() {
        System.out.println("ana f save  ");
        User user = formFactory.form(User.class).bindFromRequest().get();
        UserCommandActor userCommandActor = new UserCommandActor();
        boolean chkUsrIsCreated = userCommandActor.addUser(user);
        System.out.println("User Created : " + chkUsrIsCreated);
        return redirect(routes.UsersController.index());
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result updateUser(int id) {

        JsonNode json = request().body().asJson();


        System.out.println(" --- ana b update " + json.get(0).get("value") + " " + json.get(1).get("value") + " " + id);
        UserCommandActor userCommandActor = new UserCommandActor();
        int count = userCommandActor.updateUser(json, id);

        return redirect(routes.UsersController.index());


    }
}
