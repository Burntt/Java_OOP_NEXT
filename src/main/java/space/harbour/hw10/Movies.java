package space.harbour.hw10;

import com.mongodb.BasicDBObject;
import java.util.function.Function;
import org.bson.Document;


public class Movies {

    public static void main(String[] args) {
        Document movie = new Document("Berend", "Gort")
                .append("genre", "action");

        /////////////////////////////// STORE & RETRIEVE /////////////////////////////////
        MongoExecutor executor = new MongoExecutor();
        executor.execStoreMovie(movie);

        // Retrieve name and second name;
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("Berend", "Gort");

        Function<Document, String> handler =  s -> String.valueOf(s);
        String result = executor.execFindOne("movies", searchQuery, handler);
        System.out.println(result);


        executor.execDelMovie(movie);
        executor.execDelMovie(movie);
        executor.execDelMovie(movie);
        executor.execDelMovie(movie);



    }

}

