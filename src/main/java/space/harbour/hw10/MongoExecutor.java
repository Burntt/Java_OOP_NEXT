package space.harbour.hw10;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.function.Function;
import org.bson.Document;

public class MongoExecutor {
    MongoClient client;
    MongoDatabase database;
    MongoClientURI uri;

    public MongoExecutor() {
        uri = new MongoClientURI(
                "mongodb+srv://userHW10:VA1z0Oi1owEi4TJh@cluster0"
                        + ".ygemx.mongodb.net/<dbname>?retryWrites=true&w=majority");
        client = new MongoClient(uri);
        database = client.getDatabase("moviedb");
    }

    public <T> T execFindOne(String collection,
                             BasicDBObject searchQuery,
                             Function<Document, T> handler) {
        MongoCollection<Document> mongoCollection = database.getCollection(collection);
        FindIterable<Document> result = mongoCollection.find(searchQuery);
        return handler.apply(result.first());
    }

    public void execStoreMovie(Document document) {
        MongoCollection<Document> mongoCollection = database.getCollection("movies");
        mongoCollection.insertOne(document);
        System.out.println("Succesfully stored movie");
    }

    public void execDelMovie(Document document) {
        MongoCollection<Document> mongoCollection = database.getCollection("movies");
        mongoCollection.deleteOne(document);
        System.out.println("Succesfully deleted movie");
    }

}



