package space.harbour.hw10;

import static org.junit.Assert.assertEquals;

import com.mongodb.BasicDBObject;
import java.util.function.Function;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

public class MongoExecutorTest {
    Function<Document, String> handler = s -> String.valueOf(s);
    BasicDBObject searchQuery = new BasicDBObject();
    Document movie = new Document("Berend", "Gort")
            .append("malefemale", "male");
    Document movie2 = new Document("Test", "test1")
            .append("Test2", "test2");
    MongoExecutor executor = new MongoExecutor();

    @Before
    public void setup() {
        executor.execStoreMovie(movie);
    }

    @Test
    public void testExecRemoveMovie() {
        executor.execDelMovie(movie);
    }

    @Test
    public void testExecFindOne() {
        String result = executor.execFindOne("movies", searchQuery, handler);
        //System.out.println(result);
        assertEquals("Document{{_id=5fb7d80562443806e9638a5c, "
                + "Berend=Gort, malefemale=male}}", result);
    }

}