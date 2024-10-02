import java.util.HashMap;

public class Person {
    private final String name;
    private static HashMap<String, Person> personRegistry = new HashMap<>();

    // Private constructor to prevent direct instantiation
    private Person(String name) {
        this.name = name;
    }

    // Static method to create or return an existing person
    public static Person getOrCreatePerson(String name) {
        if (personRegistry.containsKey(name)) {
            System.out.println("Person " + name + " already exists.");
            return personRegistry.get(name);
        } else {
            Person newPerson = new Person(name);
            personRegistry.put(name, newPerson);
            System.out.println("Person " + name + " created.");
            return newPerson;
        }
    }

    public String getName() {
        return name;
    }
}
