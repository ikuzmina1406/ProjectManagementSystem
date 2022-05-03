package ua.GoIt.console.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.GoIt.console.Command;
import ua.GoIt.dao.CompaniesDao;
import ua.GoIt.model.Companies;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class CompaniesCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CompaniesCommand.class);
    private final CompaniesDao companiesDao = new CompaniesDao();

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
            LOGGER.info("--------Category menu--------------------"+"\n" + "--------Command menu----------"+"\n" +"create[!name!state_code!country!info]----read (получить инфо о всех компаниях)----update[!id!name!state_code!country!info]----delete[!id]-(удалить компанию по id )----get[!id]-(получить инфо о компании по id)----");
    }
    private void read() { //to get the list of all companies (the command : "read")
        List<Companies> all =  companiesDao.read();

        System.out.println("Returned " + all.size() + " companies");
        for(Companies companies : all){
            System.out.println(companies);
        }

    }
    private  void  get(String subParams) { // select companies by id (the command: "get!19")
        String[] paramsArray = subParams.split("!");
        Optional<Companies> companies = companiesDao.get(Long.parseLong(paramsArray[0]));
        if (companies.isPresent()) {
            System.out.println(companies.get());
        } else {
            System.out.println("companies with id " + paramsArray[0] + " is not found");
        }
    }
    private void  update (String subParams){ // companies update (the command: "update!19!Dev!12345678!Ukraine!Co "DevGames" Gamedevelopment")
        String [] paramsArray = subParams.split("!");
        Optional<Companies> optionalCompanies =  companiesDao.get(Long.parseLong(paramsArray[0]));
        if (optionalCompanies.isPresent()){
            Companies companies = optionalCompanies.get();
            companies.setName(paramsArray[1]);
            companies.setState_code(paramsArray[2]);
            companies.setCountry(paramsArray[3]);
            companies.setInfo(paramsArray[4]);
            companiesDao.update(companies);
        }else{
            System.out.println(" companies with id " + paramsArray[0] + " is not found");
        }

    }

    private void  create(String subParams){ //companies create (the command : " create!NovoProg!12385967!Ukraine!Attribute")
        String [] paramsArray = subParams.split("!");
        Companies companies = new Companies();
        companies.setName(paramsArray[0]);
        companies.setState_code(paramsArray[1]);
        companies.setCountry(paramsArray[2]);
        companies.setInfo(paramsArray[3]);
        companiesDao.create(companies);
    }



    private void  delete(String subParams){ //delete from companies by id (the command: " delete!25")
        String [] paramsArray = subParams.split("!");
        Optional<Companies> companies =  companiesDao.get(Long.parseLong(paramsArray[0]));
        if (companies.isPresent()){
            companiesDao.delete(companies.get());
        }else{
            System.out.println("companies with id " + paramsArray[0] + " is not found");
        }
    }
}
