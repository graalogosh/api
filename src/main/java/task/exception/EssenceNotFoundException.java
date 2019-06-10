package task.exception;

import java.util.UUID;

public class EssenceNotFoundException extends Exception {

    public EssenceNotFoundException(UUID id) {
        super(String.format("task.Essence is not found with id : '%s'", id));
    }
}
