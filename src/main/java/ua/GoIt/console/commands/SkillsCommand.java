package ua.GoIt.console.commands;

import ua.GoIt.dao.SkillsDao;
import ua.GoIt.console.Command;
import ua.GoIt.model.Developers;
import ua.GoIt.model.Projects;
import ua.GoIt.model.Skills;

import java.util.List;
import java.util.Optional;

public class SkillsCommand implements Command {
    SkillsDao skillsDao = new SkillsDao();

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

    private void delete(String params) { //skills delete id (the command: "skills delete 99")
        String [] paramsArray = params.split(" ");
        Optional<Skills> skills =  skillsDao.get(Long.parseLong(paramsArray[0]));
        if (skills.isPresent()){
            skillsDao.delete(skills.get());
        }else{
            System.out.println("skill with id " + paramsArray[0] + " is not found");
        }
    }

    private void get(String params) { // skills get id
        String [] paramsArray = params.split(" ");
        Optional<Skills> skills =  skillsDao.get(Long.parseLong(paramsArray[0]));
        if (skills.isPresent()){
            System.out.println(skills.get());
        }else{
            System.out.println("skill with id " + paramsArray[0] + " is not found");
        }

    }

    private void getAll( ) {
        List<Skills> all = skillsDao.getAll();

        System.out.println("Returned " + all.size() + " skills");
        for(Skills skills : all) {
            System.out.println(skills);
        }
    }
    private void update(String params) {// skills update ID BRANCH LEVEL (the command: "skills update 99 SQL intern  ")
        String [] paramsArray = params.split(" ");
        Optional<Skills> optionalSkills =  skillsDao.get(Long.parseLong(paramsArray[0]));
        if (optionalSkills.isPresent()){
            Skills skills = optionalSkills.get();
            skills.setBranch(paramsArray[1]);
            skills.setLevel(paramsArray[2]);

            skillsDao.update(skills);
        }else{
            System.out.println("skills with id " + paramsArray[0] + " is not found");
        }
    }

    private void create(String subParams) { //skills create (the command : skills create BRANCH LEVEL (" skills create SQL Junior "))
        String [] paramsArray = subParams.split(" ");
        Skills skills = new Skills();
        skills.setBranch(paramsArray[0]);
        skills.setLevel(paramsArray[1]);
        skillsDao.create(skills);


    }
}
