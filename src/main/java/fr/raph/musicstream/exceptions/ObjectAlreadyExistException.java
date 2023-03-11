package fr.raph.musicstream.exceptions;

public class ObjectAlreadyExistException extends RuntimeException {

    public ObjectAlreadyExistException(String type, String value) {
        super("Object of type " + type + " with value " + value + " already exist");
    }
}
