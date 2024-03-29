package ua.GoIt.console;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Command {
    Pattern pattern = Pattern.compile("^\\w+");

    void handle(String params, Consumer<Command> setActive) throws SQLException;

    void printActiveMenu();

    default Optional<String> getCommandString(String params) {
        Matcher matcher = pattern.matcher(params);
        if (matcher.find()) {
            return Optional.of(matcher.group());
        } else {
            return Optional.empty();
        }
    }
}