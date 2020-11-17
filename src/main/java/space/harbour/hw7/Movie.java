package space.harbour.hw7;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Movie {
    public String title;
    public double rating;
    public int year;
    public int runtime;
    public String[] genres;
    public String director;
    public String[] actors;

    public Movie(String title, double rating, int year,
                 int runtime, String[] genres, String director, String[] actors) {
        this.title = title;
        this.rating = rating;
        this.year = year;
        this.runtime = runtime;
        this.genres = genres;
        this.director = director;
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "MoviesList{" + "title='" + title + '\''
                + ", rating=" + rating
                + ", year=" + year + ", runtime=" + runtime
                + ", genres=" + Arrays.toString(genres)
                + ", director='" + director + '\''
                + ", actors=" + Arrays.toString(actors)
                + '}';
    }

    public double getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public int getRuntime() {
        return runtime;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public String[] getActors() {
        return actors;
    }

    public String[] getGenres() {
        return genres;
    }

    public static void main(String[] args) {
        ///////////// Create all movies ////////////////
        ///////////// Create all movies ////////////////
        ///////////// Create all movies ////////////////

        ArrayList<Movie> movies = new ArrayList<>();

        Movie movie = new Movie("Movie 1", 8.1, 2010,
                210, new String[]{"Sci-Fi", "action"}, "Ahmed Manji",
                new String[]{"Alex", "Alejandro"});
        movies.add(movie);

        Movie movie2 = new Movie("Movie 2", 9.7, 2014,
                150, new String[]{"adventure", "Drama"}, "Vasili",
                new String[]{"Mohammed", "Salim"});
        movies.add(movie2);

        Movie movie3 = new Movie("Movie 3", 9.2, 2018,
                90, new String[]{"Horror", "Thriller"}, "Abdullah",
                new String[]{"John", "Jimmy"});
        movies.add(movie3);

        ///////////// SORTING AND FILTERING ////////////////
        ///////////// SORTING AND FILTERING ////////////////
        ///////////// SORTING AND FILTERING ////////////////

        /////////////   Sort by release year  ////////////////
        Collections.sort(movies, Comparator.comparingInt(o -> o.year));

        System.out.println("----------------");
        System.out.println("Sort by release year");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i).year);
        }
        System.out.println("----------------");

        /////////////     Sort by length   ////////////////
        Collections.sort(movies, Comparator.comparingInt(o -> o.runtime));

        System.out.println("Sort by release length");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i).runtime);
        }
        System.out.println("----------------");

        /////////////     Sort by rating  ////////////////
        Collections.sort(movies, Comparator.comparingInt(o -> (int) o.rating));

        System.out.println("Sort by rating");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i).rating);
        }
        System.out.println("----------------");

        /////////////   Filter by director  ////////////////
        ArrayList<Movie> filteredByDirectors = (ArrayList<Movie>) movies.stream()
                .filter(mov -> mov.getDirector().contains("Abdullah"))
                .collect(Collectors.toList());
        System.out.println("----------------");
        System.out.println("Filter by director");
        filteredByDirectors.stream().forEach(mov -> System.out.println(mov.getTitle()));

        /////////////     Filter by actor   ////////////////
        ArrayList<Movie> filteredByActors = (ArrayList<Movie>) movies.stream()
                .filter(mov -> Arrays
                        .stream(mov.getActors())
                        .anyMatch(s -> s == "Mohammed"))
                .collect(Collectors.toList());
        System.out.println("----------------");
        System.out.println("Filter by actor");
        filteredByActors.stream().forEach(mov -> System.out.println(mov.getTitle()));

        /////////////     Sort by genre   ////////////////
        ArrayList<Movie> filteredByGenre = (ArrayList<Movie>) movies.stream()
                .filter(mov -> Arrays
                        .stream(mov.getGenres())
                        .anyMatch(s -> s == "Thriller"))
                .collect(Collectors.toList());
        System.out.println("----------------");
        System.out.println("Filter by thriller");
        filteredByGenre.stream().forEach(mov -> System.out.println(mov.getTitle()));

    }
}
