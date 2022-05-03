package ua.GoIt.console.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.GoIt.console.Command;
import ua.GoIt.dao.ProjectsDao;
import ua.GoIt.model.Projects;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ProjectsCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ProjectsCommand.class);
    private final ProjectsDao projectsDao = new ProjectsDao();

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
        LOGGER.info("--------Category menu----------" + "\n" + "--------Command menu----------" + "\n" + "create[!name!info!status!cost!date_creation]----read(получить инфо о всех проектах)----update[!id!name!info!status!cost!date_creation]----delete[!id]-(удалить проект по id )----get[!id]-(получить инфо о проектах по id)----");
    }


    private void get(String subParams) { // project get by id (the command: " get!24")
        String[] paramsArray = subParams.split("!");
        Optional<Projects> projects = projectsDao.get(Long.parseLong(paramsArray[0]));
        if (projects.isPresent()) {
            System.out.println(projects.get());
        } else {
            System.out.println("project with id " + paramsArray[0] + " is not found");
        }

    }

    private void read() {//to get the list of all projects (the command : "read")
        List<Projects> all = projectsDao.read();

        System.out.println("Returned " + all.size() + " projects");
        for (Projects projects : all) {
            System.out.println(projects);
        }
    }

    private void create(String subParams) { //projects create (the command : projects create NAME INFO STATUS COST DATE_CREATION (the command :"create!Currancy!change!1!50000.00!2022-02-16"))
        String[] paramsArray = subParams.split("!");
        Projects projects = new Projects();
        projects.setName(paramsArray[0]);
        projects.setInfo(paramsArray[1]);
        projects.setStatus(Integer.parseInt(paramsArray[2]));
        projects.setCost(BigDecimal.valueOf(Double.parseDouble((paramsArray[3]))));
        projects.setDate_creation(Date.valueOf(paramsArray[4]));
        projectsDao.create(projects);


    }

    private void update(String subParams) {// projects update ID NAME INFO STATUS COST DATE_CREATION (the command: " update!22!Bot!Telegram Bot to display the exchange rate!1!200000.00!2018-01-13")
        String[] paramsArray = subParams.split("!");
        Optional<Projects> optionalProjects = projectsDao.get(Long.parseLong(paramsArray[0]));
        if (optionalProjects.isPresent()) {
            Projects projects = optionalProjects.get();
            projects.setName(paramsArray[1]);
            projects.setInfo(paramsArray[2]);
            projects.setStatus(Integer.parseInt(paramsArray[3]));
            projects.setCost(BigDecimal.valueOf(Double.parseDouble(paramsArray[4])));
            projects.setDate_creation(Date.valueOf(paramsArray[5]));
            projectsDao.update(projects);
        } else {
            System.out.println("projects with id " + paramsArray[0] + " is not found");
        }
    }

    private void delete(String subParams) { //projects delete  by id (the command: "delete!32")
        String[] paramsArray = subParams.split(" ");
        Optional<Projects> projects = projectsDao.get(Long.parseLong(paramsArray[0]));
        if (projects.isPresent()) {
            projectsDao.delete(projects.get());
        } else {
            System.out.println("project with id " + paramsArray[0] + " is not found");
        }
    }


}
