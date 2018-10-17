package seedu.superta.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_STUDENT_ID = new Prefix("id/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_TUTORIAL_GROUP_NAME = new Prefix("n/");
    public static final Prefix PREFIX_TUTORIAL_GROUP_ID = new Prefix("id/");
    public static final Prefix PREFIX_GENERAL_TUTORIAL_GROUP_ID = new Prefix("tg/");
    public static final Prefix PREFIX_GENERAL_STUDENT_ID = new Prefix("st/");
    public static final Prefix PREFIX_GENERAL_ASSIGNMENT_ID = new Prefix("as/");
    public static final Prefix PREFIX_ASSIGNMENT_NAME = new Prefix("n/");
    public static final Prefix PREFIX_ASSIGNMENT_MARKS = new Prefix("m/");
    public static final Prefix PREFIX_ASSIGNMENT_MAX_MARKS = new Prefix("m/");
    public static final Prefix PREFIX_FEEDBACK = new Prefix("f/");
}
