package space.harbour.hw4;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

class ExampleValue implements Jsonable {
    Integer intValue = 10;
    String stringValue = "ABC";
    Float floatValue = .9f;
    HiddenClass hiddenClass = new HiddenClass();

    @Override
    public String toString() {
        return "ExampleValueTest{"
                + "intValue=" + intValue
                + ", stringValue='" + stringValue + '\''
                + ", floatValue=" + floatValue
                + ", hiddenClass=" + hiddenClass + '}';
    }

    @Override
    public JsonObject toJsonObject() {

        JsonObjectBuilder objectHidden = Json.createObjectBuilder();
        objectHidden.add("s", hiddenClass.stringValue);
        objectHidden.add("i", hiddenClass.intValue);

        JsonObject jsonHidden = objectHidden.build();

        JsonObject json = Json.createObjectBuilder()
                .add("i", intValue)
                .add("s", stringValue)
                .add("f", floatValue)
                .add("hiddenClass", jsonHidden)
                .build();
        return json;
    }

    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }

    public static class HiddenClass {
        String stringValue = "XYZ";
        Integer intValue = 1050;

        @Override
        public String toString() {
            return "{"
                    + "stringValue='" + stringValue + '\''
                    + ", intValue=" + intValue
                    + '}';
        }
    }

    public void fromJson(String json) {
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject jsonObject = reader.readObject();

        this.intValue = jsonObject.getInt("i");
        this.stringValue = jsonObject.getString("s");
        this.floatValue = (float) jsonObject.getJsonNumber("f").doubleValue();

        JsonObject hiddenObject = jsonObject.getJsonObject("hiddenClass");

        this.hiddenClass = new HiddenClass();
        this.hiddenClass.stringValue  = hiddenObject.getString("s");
        this.hiddenClass.intValue  = hiddenObject.getInt("i");

    }

    public static void main(String[] args) {
        try {
            System.out.println("-----------------------------------------------------");
            System.out.println("--------------- (De)serialization Task --------------");
            System.out.println("-----------------------------------------------------");

            System.out.println(" ");
            System.out.println("--------------- Serialization Task ------------------");
            System.out.println(" ");
            System.out.println("Formatting instance of ExampleValueTest class to JSON");
            ExampleValue value = new ExampleValue();
            System.out.println(value.toJsonString());

            System.out.println(" ");
            System.out.println("--------------- Deserialization Task ------------------");
            System.out.println(" ");
            System.out.println("class to JSON, and INSTANCE -> JSON -> JSONTOSTRING -> FROMJSON");
            ExampleValue value2 = new ExampleValue();
            value2.fromJson(value.toJsonString());
            System.out.println(value2);

            System.out.println(" ");
            System.out.println("-----------------------------------------------------");
            System.out.println("Class succesfully serialized and deserialized");
            System.out.println("-----------------------------------------------------");

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
    }
}
