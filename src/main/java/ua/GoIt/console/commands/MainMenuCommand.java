package ua.GoIt.console.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.GoIt.console.Command;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class MainMenuCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(MainMenuCommand.class);

    Map<String, Command> commands = Map.of(
            "developers", new DevelopersCommand(),
            "skills", new SkillsCommand(),
            "projects", new ProjectsCommand(),
            "customers", new CustomersCommand(),
            "companies", new CompaniesCommand(),
            "functional", new CommandFunctional()
    );

    @Override
    public void handle(String params, Consumer<Command> setActive) {
        Optional<String> commandString = getCommandString(params);
        commandString.map(commands::get)
                .ifPresent(command -> {
                    setActive.accept(command);
                    try {
                        command.handle(params.replace(commandString.get(),
                                "").trim(), setActive);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void printActiveMenu() {
        LOGGER.info("-----------------------------Main menu------------------------------");
        LOGGER.info("Command list: " + this.commands.keySet() + "\n" + "                                                                                          create--read--update--delete--get" + "\n" + "                                                                                                 " + "functional - доп операции" );
        LOGGER.info("          -------------------close console - exit--------------------");
    }
}