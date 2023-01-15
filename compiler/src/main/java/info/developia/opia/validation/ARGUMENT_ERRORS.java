package info.developia.opia.validation;

/**
 * Created by kuba on 16.03.16.
 */
public enum ARGUMENT_ERRORS {
    NONE(""),
    NO_FILE("Specify files for compilation"),
    BAD_FILE_EXTENSION("File has to end with .opia extension");

    private final String message;

    ARGUMENT_ERRORS(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
