package ua.GoIt.console.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.GoIt.dao.DevelopersDao;
import ua.GoIt.console.Command;
import ua.GoIt.model.Developers;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class DevelopersCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(DevelopersCommand.class);
    private final DevelopersDao developersDao = new DevelopersDao();
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
        LOGGER.info("--------Category menu--------------------"+"\n" + "--------Command menu----------"+"\n" +"create--get id--getAll--delete--update");
    }

    private void getAll() { //to get the list of all developers (the command : "getAll")
        List<Developers> all =  developersDao.getAll();

        System.out.println("Returned " + all.size() + " developers");
        for(Developers developers : all){
            System.out.println(developers);
        }
//        try {
//            System.out.println(developersDao.getAll());
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }
    private  void  get(String subParams) { // select developers by id (the command: "get 22")
        String[] paramsArray = subParams.split(" ");
        Optional<Developers> developers = developersDao.get(Long.parseLong(paramsArray[0]));
        if (developers.isPresent()) {
            System.out.println(developers.get());
        } else {
            System.out.println("developer with id " + paramsArray[0] + " is not found");
        }
    }
    private void  update (String subParams){ // developers update (the command: " update 24 Maksym Yanov Maksymovych 25 1996-02-17 male Ukraine 4444444444 Lviv,KropuvnutskogoStr7a,ap8 1 130000.00")
        String [] paramsArray = subParams.split(" ");
        Optional<Developers> optionalDevelopers =  developersDao.get(Long.parseLong(paramsArray[0]));
        if (optionalDevelopers.isPresent()){
            Developers developers = optionalDevelopers.get();
            developers.setName(paramsArray[1]);
            developers.setFirst_name(paramsArray[2]);
            developers.setLast_name(paramsArray[3]);
            developers.setAge(Integer.parseInt(paramsArray[4]));
            developers.setBirthday(Date.valueOf(paramsArray[5]));
            developers.setSex(paramsArray[6]);
            developers.setCountry(paramsArray[7]);
            developers.setState_code(Long.valueOf(paramsArray[8]));
            developers.setAddress(paramsArray[9]);
            developers.setStatus(Integer.parseInt(paramsArray[10]));
            developers.setSalary(BigDecimal.valueOf(Double.parseDouble(paramsArray[11])));
            developersDao.update(developers);
        }else{
            System.out.println("developer with id " + paramsArray[0] + " is not found");
        }

    }

    private void  create(String subParams){ //developers create (the command : " create Ekateryna Klynskaya Alexandrovna 38 1983-03-23 female Poland 1238569784 02131,Gasheka25,ap21 1 80000.00")
        String [] paramsArray = subParams.split(" ");
        Developers developers = new Developers();
        developers.setName(paramsArray[0]);
        developers.setFirst_name(paramsArray[1]);
        developers.setLast_name(paramsArray[2]);
        developers.setAge(Integer.parseInt(paramsArray[3]));
        developers.setBirthday(Date.valueOf(paramsArray[4]));
        developers.setSex(paramsArray[5]);
        developers.setCountry(paramsArray[6]);
        developers.setState_code(Long.parseLong(paramsArray[7]));
        developers.setAddress(paramsArray[8]);
        developers.setStatus(Integer.parseInt(paramsArray[9]));
        developers.setSalary(BigDecimal.valueOf(Double.parseDouble((paramsArray[10]))));
        developersDao.create(developers);
    }



    private void  delete(String subParams){ //delete from developers by id (the command: " delete 59")
        String [] paramsArray = subParams.split(" ");
        Optional<Developers> developers =  developersDao.get(Long.parseLong(paramsArray[0]));
        if (developers.isPresent()){
            developersDao.delete(developers.get());
        }else{
            System.out.println("developer with id " + paramsArray[0] + " is not found");
        }
    }
}
