package ua.GoIt.console.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.GoIt.dao.SkillsDao;
import ua.GoIt.console.Command;
import ua.GoIt.model.Skills;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class SkillsCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(SkillsCommand.class);
    SkillsDao skillsDao = new SkillsDao();

    @Override
    public void handle(String params, Consumer<Command> setActive) {
        String[] paramsArray = params.split("!");
        String subParams = String.join("!", params.replace(paramsArray[0] + "!", ""));
        switch (paramsArray[0]) {
            case "create":
                create(subParams);
                break;
            case "read":
                read();
                break;
            case "update":
                update(subParams);
                break;
            case "delete":
                delete(subParams);
                break;
            case "get":
                get(subParams);
                break;
        }
    }

    @Override
    public void printActiveMenu() {
        LOGGER.info("--------Category menu----------" + "\n" + "--------Command menu----------" + "\n" + "create[!branch!level]----read(получить инфо о всех специальностях)----delete[!id]-(удалить специальность по id )----update[!id!branch!level]----get[!id]-(получить инфо о специальностях по id)----");
    }


    private void get(String subParams) { // skills get by id (the command: " get!96")
        String[] paramsArray = subParams.split("!");
        Optional<Skills> skills = skillsDao.get(Long.parseLong(paramsArray[0]));
        if (skills.isPresent()) {
            System.out.println(skills.get());
        } else {
            System.out.println("skill with id " + paramsArray[0] + " is not found");
        }

    }

    private void read() {//to get the list of all skills (the command : "read")
        List<Skills> all = skillsDao.read();

        System.out.println("Returned " + all.size() + " skills");
        for (Skills skills : all) {
            System.out.println(skills);
        }
    }


    private void create(String subParams) { //skills create (the command : skills create BRANCH LEVEL ("create!SQL!Junior"))
        String[] paramsArray = subParams.split("!");
        Skills skills = new Skills();
        skills.setBranch(paramsArray[0]);
        skills.setLevel(paramsArray[1]);
        skillsDao.create(skills);


    }

    private void update(String subParams) {// skills update ID BRANCH LEVEL (the command: "update!101!SQL!trainee")
        String[] paramsArray = subParams.split("!");
        Optional<Skills> optionalSkills = skillsDao.get(Long.parseLong(paramsArray[0]));
        if (optionalSkills.isPresent()) {
            Skills skills = optionalSkills.get();
            skills.setBranch(paramsArray[1]);
            skills.setLevel(paramsArray[2]);

            skillsDao.update(skills);
        } else {
            System.out.println("skills with id " + paramsArray[0] + " is not found");
        }
    }

    private void delete(String subParams) { //skills delete by id (the command: "delete!101")
        String[] paramsArray = subParams.split(" ");
        Optional<Skills> skills = skillsDao.get(Long.parseLong(paramsArray[0]));
        if (skills.isPresent()) {
            skillsDao.delete(skills.get());
        } else {
            System.out.println("skill with id " + paramsArray[0] + " is not found");
        }
    }
}
