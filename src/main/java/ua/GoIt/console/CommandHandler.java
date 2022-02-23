package ua.GoIt.console;



import ua.GoIt.console.commands.CustomersCommand;
import ua.GoIt.console.commands.DevelopersCommand;
import ua.GoIt.console.commands.ProjectsCommand;
import ua.GoIt.console.commands.SkillsCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {

    Map<String, ua.GoIt.console.Command> commandMap = new HashMap<>();

    public CommandHandler() {
        commandMap.put("developers", new DevelopersCommand());
        commandMap.put("skills", new SkillsCommand());
        commandMap.put("projects", new ProjectsCommand());
        commandMap.put("customers", new CustomersCommand());
    }

    public void handleCommand(String params) {
        int firstSpace = params.indexOf(" ");
        if (firstSpace > -1) {
            Command command = commandMap
                    .get(params.substring(0, firstSpace));
            if (command != null) {
                command.handle(params.substring(firstSpace + 1));
            } else {
                System.out.println("Unknown command");
            }
        } else {
            System.out.println("Unknown command");
        }
    }
    }


