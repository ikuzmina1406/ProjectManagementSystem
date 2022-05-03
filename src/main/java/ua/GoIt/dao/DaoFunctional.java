package ua.GoIt.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface DaoFunctional<T> {

    BigDecimal sumOfSalaryDevAtProject(T entity);

    List<String> getDevelopersOfProject(T entity) throws SQLException;

    List<String> getJavaDevelopers();

    List<String> getMiddleDevelopers();

    List<String> ListOfProjectByFormat();


}
