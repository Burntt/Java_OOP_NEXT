package space.harbour.hw5;

public class GenericExampleHw5 {
    private Object value;

    public GenericExampleHw5(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public static void main(String[] args) {
        GenericExampleHw5 intObject = new GenericExampleHw5(1);
        Integer valueInteger = (Integer) intObject.getValue();

        GenericExampleHw5 stringObject = new GenericExampleHw5("word");
        String valueString = (String) stringObject.getValue();

        GenericExampleHw5 doubleObject = new GenericExampleHw5(2.15);
        Object object = doubleObject.getValue();

        GenericExampleHw5 floatObject = new GenericExampleHw5(1.5f);
        Float valueFloat = (Float) floatObject.getValue();
    }
}
