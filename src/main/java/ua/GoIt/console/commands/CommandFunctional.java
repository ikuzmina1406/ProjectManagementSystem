package ua.GoIt.console.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.GoIt.console.Command;
import ua.GoIt.dao.DevelopersDao;
import ua.GoIt.dao.ProjectsDao;
import ua.GoIt.dao.SkillsDao;
import ua.GoIt.model.Projects;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;

public class CommandFunctional implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CommandFunctional.class);
    private final DevelopersDao developersDao = new DevelopersDao();
    private final ProjectsDao projectsDao = new ProjectsDao();
    private final SkillsDao skillsDao = new SkillsDao();

    @Override
    public void handle(String params, Consumer<Command> setActive) throws SQLException {
        String[] paramsArray = params.split(" ");
        String subParams = String.join("", params.replace(paramsArray[0] + " ", ""));
        switch (paramsArray[0]) {
            case "salary":
                sum(subParams);
                break;
            case "list":
                ListOfDevelopers(subParams);
                break;
            case "java":
                ListOfJavaDevelopers (subParams);
                break;
            case "middle":
                ListOfMiddleDevelopers (subParams);
                break;
            case "projects_list":
                ListOfCountDevelopers(subParams);
                break;
        }
    }


    @Override
    public void printActiveMenu() {
        LOGGER.info("--------Category menu--------------------" + "\n" + "--------Command menu----------" + "\n" +
                "[salary] + [project name]-зарплата разработчиков отдельного проэкта" + "\n" +
                "[list] + [project name] -список разработчиков отдельного проекта " + "\n" +
                "[java]   -список java-разработчиков "+ "\n" +
                "[middle] -список всех middle разработчиков"+"\n"+
                "[projects_list] -список проектов в  формате"
        );
    }


    public void sum(String subParams) throws SQLException {
        Matcher matcher = pattern.matcher(subParams);
        if (matcher.find()) {
            String firstWord = matcher.group();
            Projects project = new Projects();
            project.setName(firstWord);
            BigDecimal bigDecimal = projectsDao.sumOfSalaryDevAtProject(project);
            System.out.println("SALARY: " + bigDecimal);
        }
    }

    public void ListOfDevelopers(String subParams){
        Matcher matcher = pattern.matcher(subParams);
        if (matcher.find()) {
            String firstWord = matcher.group();
            Projects project = new Projects();
            project.setName(firstWord);
            List<String> developersOfProject = projectsDao.getDevelopersOfProject(project);
            developersOfProject.forEach(dev -> System.out.println("DEVELOPER: " + dev));
        }
    }

    public void ListOfJavaDevelopers(String subParams) {
        List<String> developersOfJava = skillsDao.getJavaDevelopers();
        developersOfJava.forEach(dev -> System.out.println("DEVELOPER OF JAVA: " + dev));
    }
    public void ListOfMiddleDevelopers(String subParams) {
        List<String> developersOfMiddle = skillsDao.getMiddleDevelopers();
        developersOfMiddle.forEach(dev -> System.out.println("DEVELOPER OF MIDDLE: " + dev));
    }
    public void  ListOfCountDevelopers (String subParams){
    List<String> developersOfProjectFormat = developersDao.ListOfProjectByFormat();
    developersOfProjectFormat.forEach(dev -> System.out.println("THE LIST OF PROJECTS BY THE FORMAT: " + dev));
}
}