package ua.GoIt.console.commands;

import ua.GoIt.dao.CustomersDao;
import ua.GoIt.console.Command;
import ua.GoIt.model.Customers;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class CustomersCommand implements Command {
    private final CustomersDao customersDao = new CustomersDao();
    @Override
    public void handle(String params) {
        String [] paramsArray = params.split(" ");
        String subParams = String.join(" ", params.replace(paramsArray[0] + " ", ""));
        switch (paramsArray[0]){
            case  "create" : create(subParams);break;
            case  "get" : get(subParams);break;
            case  "getAll" : getAll();break;
            case  "delete" : delete(subParams);break;
            case  "update" : update(subParams);break;
        }

    }

    private void getAll() { //to get the list of all customers (the command : "customers getAll")
        List<Customers> all =  customersDao.getAll();

        System.out.println("Returned " + all.size() + " customers");
        for(Customers customers : all){
            System.out.println(customers);
        }

    }
    private void  update (String params){ // customers update (the command: "customers update 16 ProduktoFFplus 032102 Ukraine 1993-02-17 company foodmarket")
        String [] paramsArray = params.split(" ");
        Optional<Customers> optionalCustomers =  customersDao.get(Long.parseLong(paramsArray[0]));
        if (optionalCustomers.isPresent()){
            Customers customers = optionalCustomers.get();
            customers.setName(paramsArray[1]);
            customers.setState_code(paramsArray[2]);
            customers.setCountry(paramsArray[3]);
            customers.setBirthday(Date.valueOf(paramsArray[4]));
            customers.setSex(paramsArray[5]);
            customers.setInfo(paramsArray[6]);
            customersDao.update(customers);
        }else{
            System.out.println("customer with id " + paramsArray[0] + " is not found");
        }

    }

    private void  create(String params){ //customers create (the command : "customers create Sidorov 03210231 Ukraine 1976-02-17 man foodstore")
        String [] paramsArray = params.split(" ");
        Customers customers = new Customers();
        customers.setName(paramsArray[0]);
        customers.setState_code(paramsArray[1]);
        customers.setCountry(paramsArray[2]);
        customers.setBirthday(Date.valueOf(paramsArray[3]));
        customers.setSex(paramsArray[4]);
        customers.setInfo(paramsArray[5]);
        customersDao.create(customers);
    }

    private void  get(String params){ // select customers from id (the command: "customers get 15")
        String [] paramsArray = params.split(" ");
        Optional<Customers> customers =  customersDao.get(Long.parseLong(paramsArray[0]));
        if (customers.isPresent()){
            System.out.println(customers.get());
        }else{
            System.out.println("customer with id " + paramsArray[0] + " is not found");
        }
    }
    private void  delete(String params){ //delete from customers (the command: "customers delete 20")
        String [] paramsArray = params.split(" ");
        Optional<Customers> customers =  customersDao.get(Long.parseLong(paramsArray[0]));
        if (customers.isPresent()){
            customersDao.delete(customers.get());
        }else{
            System.out.println("customer with id " + paramsArray[0] + " is not found");
        }
    }
}


