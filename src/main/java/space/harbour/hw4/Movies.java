package space.harbour.hw4;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/////////////////////////////
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonWriter;


public class Movies implements Jsonable {

    String title;
    Integer year;
    String released;
    Integer runtime;
    String[] genres;
    Director director;
    Writer[] writers;
    Actor[] actors;
    String plot;
    String[] languages;
    String[] countries;
    String awards;
    String poster;
    Rating[] ratings;

    public void readFromJsonFile(String filename) throws FileNotFoundException {
        FileInputStream fin = new FileInputStream(filename);
        BufferedInputStream bin = new BufferedInputStream(fin);
        JsonReader reader = Json.createReader(bin);
        JsonObject jsonObject = reader.readObject();
        this.title = jsonObject.getString("Title");
        this.year = jsonObject.getInt("Year");
        this.released = jsonObject.getString("Released");
        this.runtime = jsonObject.getInt("Runtime");

        JsonArray genresArray = jsonObject.getJsonArray("Genres");
        this.genres = new String[genresArray.size()];
        for (int i = 0; i < this.genres.length; i++) {
            this.genres[i] = genresArray.get(i).toString();
        }

        JsonObject jsonDirector = jsonObject.getJsonObject("Director");
        this.director = new Director();
        this.director.name = jsonDirector.getString("Name");

        JsonArray writerArray = jsonObject.getJsonArray("Writers");
        this.writers = new Writer[writerArray.size()];
        for (int i = 0; i < this.writers.length; i++) {
            this.writers[i] = new Writer();
            this.writers[i].name = writerArray.getJsonObject(i).getString("Name");
            this.writers[i].type = writerArray.getJsonObject(i).getString("Type");
        }

        JsonArray actorArray = jsonObject.getJsonArray("Actors");
        this.actors = new Actor[actorArray.size()];
        for (int i = 0; i < this.actors.length; i++) {
            this.actors[i] = new Actor();
            this.actors[i].name = actorArray.getJsonObject(i).getString("Name");
            this.actors[i].as = actorArray.getJsonObject(i).getString("As");
        }

        this.plot = jsonObject.getString("Plot");

        JsonArray languagesArray = jsonObject.getJsonArray("Languages");
        this.languages = new String[languagesArray.size()];
        for (int i = 0; i < this.languages.length; i++) {
            this.languages[i] = languagesArray.get(i).toString();
        }

        JsonArray countriesArray = jsonObject.getJsonArray("Countries");
        this.countries = new String[countriesArray.size()];
        for (int i = 0; i < this.countries.length; i++) {
            this.countries[i] = countriesArray.get(i).toString();
        }

        this.awards = jsonObject.getString("Awards");
        this.poster = jsonObject.getString("Poster");

        // Ratings array of objects
        JsonArray ratingArray = jsonObject.getJsonArray("Ratings");
        this.ratings = new Rating[ratingArray.size()];
        for (int i = 0; i < this.ratings.length; i++) {
            this.ratings[i] = new Rating();
            this.ratings[i].source = ratingArray.getJsonObject(i).getString("Source");
            this.ratings[i].value = ratingArray.getJsonObject(i).getString("Value");
            this.ratings[i].votes = ratingArray.getJsonObject(i).getInt("Votes", 0);

        }

    }

    @Override
    public JsonObject toJsonObject() {

        // Genre
        JsonArrayBuilder arrayGenres = Json.createArrayBuilder();
        for (String s : genres) {
            arrayGenres.add(s);
        }

        //Director
        JsonObjectBuilder objectDirector = Json.createObjectBuilder();
        objectDirector.add("Name", director.name);

        //Writer
        JsonArrayBuilder arrayWriter = Json.createArrayBuilder();
        for (Writer s : writers) {
            JsonObjectBuilder objectWriter = Json.createObjectBuilder();
            objectWriter.add("Name", s.name);
            objectWriter.add("Type", s.type);
            JsonObject jsonWriters = objectWriter.build();
            arrayWriter.add(jsonWriters);
        }

        //Actors
        JsonArrayBuilder arrayActor = Json.createArrayBuilder();
        for (Actor s : actors) {
            JsonObjectBuilder objectActor = Json.createObjectBuilder();
            objectActor.add("Name", s.name);
            objectActor.add("As", s.as);
            JsonObject jsonActor = objectActor.build();
            arrayActor.add(jsonActor);
        }

        // Languages
        JsonArrayBuilder arrayLanguages = Json.createArrayBuilder();
        for (String s : languages) {
            arrayLanguages.add(s);
        }

        // Countries
        JsonArrayBuilder arrayCountries = Json.createArrayBuilder();
        for (String s : countries) {
            arrayCountries.add(s);
        }

        // Ratings
        JsonArrayBuilder arrayRatings = Json.createArrayBuilder();
        for (Rating s : ratings) {
            JsonObjectBuilder objectRating = Json.createObjectBuilder();
            objectRating.add("Source", s.source);
            objectRating.add("Value", s.value);
            objectRating.add("Votes", s.votes);
            JsonObject jsonRatings = objectRating.build();
            arrayRatings.add(jsonRatings);
        }
        JsonArray jsonGenres = arrayGenres.build();
        JsonObject jsonDirector = objectDirector.build();
        JsonArray jsonWriters = arrayWriter.build();
        JsonArray jsonActor = arrayActor.build();
        JsonArray jsonLanguages = arrayLanguages.build();
        JsonArray jsonCountries = arrayCountries.build();
        JsonArray jsonRating = arrayRatings.build();

        JsonObject json = Json.createObjectBuilder()
                .add("Title", title)
                .add("Year", year)
                .add("Released", released)
                .add("Runtime", runtime)
                .add("Genres", jsonGenres)
                .add("Director", jsonDirector)
                .add("Writers", jsonWriters)
                .add("Actor", jsonActor)
                .add("Plot", plot)
                .add("Languages", jsonLanguages)
                .add("Countries", jsonCountries)
                .add("Awards", awards)
                .add("Poster", poster)
                .add("Ratings", jsonRating)
                .build();
        return json;
    }

    public static void writeObjectToFile(JsonObject obj, String fileName) throws IOException {
        FileOutputStream fout = new FileOutputStream(fileName);
        JsonWriter writer = Json.createWriter(fout);
        writer.writeObject(obj);
        writer.close();
    }

    public static void main(String[] args) {
        Movies readMovies = new Movies();
        try {
            readMovies.readFromJsonFile("./src/main/java/space/harbour/hw4/BladeRunner.json");
            System.out.println("Print something:");
            System.out.println(readMovies.toJsonString());
            writeObjectToFile(readMovies.toJsonObject(),
                    "./src/main/java/space/harbour/hw4/BladeRunnerWritten.json");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }

    public static class Director {
        String name;
    }

    public static class Writer {
        String name;
        String type;

    }

    public static class Actor {
        String name;
        String as;
    }

    public static class Rating {
        String source;
        String value;
        int votes;
    }

}

