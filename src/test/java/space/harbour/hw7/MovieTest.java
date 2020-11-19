package space.harbour.hw7;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;
import space.harbour.hw7.Movie;

public class MovieTest {

    ArrayList<Movie> movies = new ArrayList<>();
    public Movie movie;
    public Movie movie2;
    public Movie movie3;

    @Before
    public void setup() {
        movie = new Movie("Movie 1", 8.1, 2010,
                210, new String[]{"Sci-Fi", "action"}, "Ahmed Manji",
                new String[]{"Alex", "Alejandro"});
        movies.add(movie);

        movie2 = new Movie("Movie 2", 9.7, 2014,
                150, new String[]{"adventure", "Drama"}, "Vasili",
                new String[]{"Mohammed", "Salim"});
        movies.add(movie2);

        movie3 = new Movie("Movie 3", 9.2, 2018,
                90, new String[]{"Horror", "Thriller"}, "Abdullah",
                new String[]{"John", "Jimmy"});
        movies.add(movie3);

        System.out.println("works?");
    }

    @Test

    /////////////   Sort by release year  ////////////////
    public void sortByYear() {
        Collections.sort(movies, Comparator.comparingInt(o -> o.year));

        int[] intArray = new int[]{2010, 2014, 2018};

        for (int i = 0; i < movies.size(); i++) {
            assertEquals(intArray[i], movies.get(i).year);
        }
    }

    @Test
    /////////////   Sort by length  ////////////////
    public void sortByLength() {
        Collections.sort(movies, Comparator.comparingInt(o -> o.runtime));

        int[] intArray = new int[]{90, 150, 210};

        for (int i = 0; i < movies.size(); i++) {
            assertEquals(intArray[i], movies.get(i).runtime);
        }
    }

    @Test
    /////////////   Sort by rating  ////////////////
    public void sortByRating() {
        Collections.sort(movies, Comparator.comparingDouble(o -> o.rating));
        double[] doubleArray = new double[]{8.1, 9.2, 9.7};
        for (int i = 0; i < movies.size(); i++) {
            assertEquals(doubleArray[i], movies.get(i).rating, 0.1);
        }
    }

    @Test
    /////////////   filter by director  ////////////////
    public void setFilteredByDirectors() {
        ArrayList<Movie> filteredByDirectors = (ArrayList<Movie>) movies.stream()
                .filter(mov -> mov.getDirector().contains("Abdullah"))
                .collect(Collectors.toList());
        assertTrue(filteredByDirectors.contains(movie3));
        assertEquals(filteredByDirectors.size(), 1);

    }

    @Test
    /////////////   filter by actor  ////////////////
    public void setFilteredByActor() {
        ArrayList<Movie> filteredByActors = (ArrayList<Movie>) movies.stream()
                .filter(mov -> Arrays
                        .stream(mov.getActors())
                        .anyMatch(s -> s == "Mohammed"))
                .collect(Collectors.toList());
        assertTrue(filteredByActors.contains(movie2));
        assertEquals(filteredByActors.size(), 1);
    }

    @Test
    /////////////   filter by genre  ////////////////
    public void setFilteredByGenre() {
        ArrayList<Movie> filteredByGenre = (ArrayList<Movie>) movies.stream()
                .filter(mov -> Arrays
                        .stream(mov.getGenres())
                        .anyMatch(s -> s == "Thriller"))
                .collect(Collectors.toList());
        assertTrue(filteredByGenre.contains(movie3));
        assertEquals(filteredByGenre.size(), 1);
    }


}