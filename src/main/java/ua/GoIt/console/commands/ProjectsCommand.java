package ua.GoIt.console.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.GoIt.dao.ProjectsDao;
import ua.GoIt.console.Command;
import ua.GoIt.model.Projects;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ProjectsCommand implements Command {
 private static  final Logger LOGGER = LogManager.getLogger(ProjectsCommand.class);
    private final ProjectsDao projectsDao = new ProjectsDao();
    @Override
    public void handle(String params, Consumer<Command> setActive) {
        String[] paramsArray = params.split(" ");
        String subParams = String.join(" ", params.replace(paramsArray[0]+ " ", ""));
        switch (paramsArray[0]) {
            case "create": create(subParams);break;
            case "get": get(subParams);break;
            case "getAll": getAll();break;
            case "delete": delete(subParams);break;
            case "update": update(subParams);break;
        }
    }

    @Override
    public void printActiveMenu() {
        LOGGER.info("--------Category menu----------"+"\n" + "--------Command menu----------"+"\n" +"create--get id--getAll--delete--update");
    }



    private void get(String subParams) { // project get by id (the command: " get 24")
        String [] paramsArray = subParams.split(" ");
        Optional<Projects> projects =  projectsDao.get(Long.parseLong(paramsArray[0]));
        if (projects.isPresent()){
            System.out.println(projects.get());
        }else{
            System.out.println("project with id " + paramsArray[0] + " is not found");
        }

    }

    private void getAll( ) {//to get the list of all projects (the command : "getAll")
        List<Projects> all = projectsDao.getAll();

        System.out.println("Returned " + all.size() + " projects");
        for(Projects projects : all) {
            System.out.println(projects);
        }
    }
    private void create(String subParams) { //projects create (the command : projects create NAME INFO STATUS COST (the command :"create Currancy change 1 50000.00 2022-02-16 "))
        String [] paramsArray = subParams.split(" ");
        Projects projects = new Projects();
        projects.setName(paramsArray[0]);
        projects.setInfo(paramsArray[1]);
        projects.setStatus(Integer.parseInt(paramsArray[2]));
        projects.setCost(BigDecimal.valueOf(Double.parseDouble((paramsArray[3]))));
        projects.setDateCreation(Date.valueOf(paramsArray[4]));
        projectsDao.create(projects);


    }
    private void update(String subParams) {// projects update ID NAME INFO STATUS COST (the command: " update 31 ConCurrancy change 1 50000.00 2022-02-16  ")
        String [] paramsArray = subParams.split(" ");
        Optional<Projects> optionalProjects =  projectsDao.get(Long.parseLong(paramsArray[0]));
        if (optionalProjects.isPresent()){
            Projects projects = optionalProjects.get();
            projects.setName(paramsArray[1]);
            projects.setInfo(paramsArray[2]);
            projects.setStatus(Integer.parseInt(paramsArray[3]));
            projects.setCost(BigDecimal.valueOf(Double.parseDouble(paramsArray[4])));
            projects.setDateCreation(Date.valueOf(paramsArray[5]));
            projectsDao.update(projects);
        }else{
            System.out.println("projects with id " + paramsArray[0] + " is not found");
        }
    }
    private void delete(String subParams) { //projects delete  by id (the command: " delete 31")
        String [] paramsArray = subParams.split(" ");
        Optional<Projects> projects =  projectsDao.get(Long.parseLong(paramsArray[0]));
        if (projects.isPresent()){
            projectsDao.delete(projects.get());
        }else{
            System.out.println("project with id " + paramsArray[0] + " is not found");
        }
    }

}
