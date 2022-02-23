package ua.GoIt.console.commands;

import ua.GoIt.dao.ProjectsDao;
import ua.GoIt.console.Command;
import ua.GoIt.model.Developers;
import ua.GoIt.model.Projects;
import ua.GoIt.model.Skills;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class ProjectsCommand implements Command {
    //    private static  final Logger LOGGER = LogManager.getLogger(ProjectsCommand.class);
    private final ProjectsDao projectsDao = new ProjectsDao();
    @Override
    public void handle(String params) {
        String[] paramsArray = params.split(" ");
        String subParams = String.join(" ", params.replace(paramsArray[0] + " ", ""));
        switch (paramsArray[0]) {
            case "create":
                create(subParams);
                break;
            case "get": get(subParams);
                break;
            case "getAll": getAll();
                break;
            case "delete": delete(subParams);
                break;
            case "update": update(subParams);
                break;

        }
    }

    private void delete(String params) { //projects delete id (the command: "projects delete 30")
        String [] paramsArray = params.split(" ");
        Optional<Projects> projects =  projectsDao.get(Long.parseLong(paramsArray[0]));
        if (projects.isPresent()){
            projectsDao.delete(projects.get());
        }else{
            System.out.println("project with id " + paramsArray[0] + " is not found");
        }
    }

    private void get(String params) { // project get id (the command: "projects get 24")
        String [] paramsArray = params.split(" ");
        Optional<Projects> projects =  projectsDao.get(Long.parseLong(paramsArray[0]));
        if (projects.isPresent()){
            System.out.println(projects.get());
        }else{
            System.out.println("project with id " + paramsArray[0] + " is not found");
        }

    }

    private void getAll( ) {
        List<Projects> all = projectsDao.getAll();

        System.out.println("Returned " + all.size() + " projects");
        for(Projects projects : all) {
            System.out.println(projects);
        }
    }
    private void update(String params) {// projects update ID NAME INFO STATUS COST (the command: "projects update 30 ConCurrancy change 1 50000.00 2022-02-16  ")
        String [] paramsArray = params.split(" ");
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

    private void create(String params) { //projects create (the command : projects create NAME INFO STATUS COST (" projects create Currancy change 1 50000.00 2022-02-16 "))
        String [] paramsArray = params.split(" ");
        Projects projects = new Projects();
        projects.setName(paramsArray[0]);
        projects.setInfo(paramsArray[1]);
        projects.setStatus(Integer.parseInt(paramsArray[2]));
        projects.setCost(BigDecimal.valueOf(Double.parseDouble((paramsArray[3]))));
        projects.setDateCreation(Date.valueOf(paramsArray[4]));
        projectsDao.create(projects);


    }
}
