package fr.raph.musicstream.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String type, String value) {
        super("Object of type " + type + " with value " + value + " not found");
    }
}
