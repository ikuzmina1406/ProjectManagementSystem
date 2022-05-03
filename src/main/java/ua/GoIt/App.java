package ua.GoIt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.GoIt.config.DbMigration;
import ua.GoIt.console.CommandHandler;

import java.sql.SQLException;
import java.util.Scanner;


public class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class);

    public static void main(String[] args) throws SQLException {
        LOGGER.debug("start application");
        DbMigration.migrate();

        runMainApp();
        LOGGER.info("end application");


    }

    public static void runMainApp() throws SQLException {
        CommandHandler commandHandler = new CommandHandler();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            commandHandler.handleCommand(scanner.nextLine());
        }

    }
}


