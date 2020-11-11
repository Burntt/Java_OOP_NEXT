package space.harbour.hw3;

public class GenericExample {
    private Object value;

    public GenericExample(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public static void main(String[] args) {
        GenericExample intObject = new GenericExample(1);
        Integer valueInteger = (Integer) intObject.getValue();

        GenericExample stringObject = new GenericExample("word");
        String valueString = (String) stringObject.getValue();

        GenericExample doubleObject = new GenericExample(2.15);
        Object object = doubleObject.getValue();

        GenericExample floatObject = new GenericExample(1.5f);
        Float valueFloat = (Float) floatObject.getValue();
    }
}
