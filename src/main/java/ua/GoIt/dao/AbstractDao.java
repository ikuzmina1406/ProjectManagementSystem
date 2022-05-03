package ua.GoIt.dao;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AbstractDao<T extends Identity> implements Dao<T>, DaoFunctional<T> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractDao.class);

    abstract String getTableName();

    abstract T mapToEntity(ResultSet resultSet) throws SQLException;

    @Override
    public void delete(T entity) {
        String sql = String.format("DELETE FROM %s WHERE id = ?", getTableName());

        PgUtil.executeWithPrepareStatement(sql, ps -> {
            ps.setLong(1, entity.getId());

        });
        LOGGER.info(" DELETED RECORDS " + getTableName());
    }

    @Override
    public Optional<T> get(Long id) {
        String query = String.format("SELECT * FROM %s where id = ?", getTableName());

        try {
            ResultSet resultSet = PgUtil.getWithPrepareStatement(query, ps -> {
                ps.setLong(1, id);
            });
            if (resultSet.next()) {
                LOGGER.debug(" RECORD WAS SELECTED ");
                return Optional.of(mapToEntity(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<T> read() {
        List<T> resultList = new ArrayList<>();
        String query = String.format("SELECT * FROM %s", getTableName());
        try {
            ResultSet resultSet = PgUtil.getWithPrepareStatement(query, ps -> {
            });
            while (resultSet.next()) {

                resultList.add(mapToEntity(resultSet));

            }
        } catch (SQLException e) {
            LOGGER.error("`READ` METHOD EXCEPTION", e);

        }


        return resultList;
    }

    private BigDecimal getSalary(ResultSet resultSet) throws SQLException {
        return resultSet.getBigDecimal("sum");
    }

    private String getSkillsName(ResultSet resultSet) throws SQLException {
        return resultSet.getString("name");
    }

    private String getString(ResultSet resultSet) throws SQLException {
        return resultSet.getString("date_creation") +
                " " +
                resultSet.getString("name") +
                " " +
                resultSet.getString("count");
    }

    @Override
    public BigDecimal sumOfSalaryDevAtProject(T entity) {
        AtomicReference<BigDecimal> sum = new AtomicReference<>();
        String query = String.format("SELECT SUM(salary) FROM developer_project INNER JOIN developers ON developers.id = developer_project.developer_id INNER JOIN projects ON projects.id = developer_project.project_id WHERE projects.name = ? GROUP BY projects.name");
        try {
            PgUtil.getWithPrepareStatement(query, ps -> {
                ps.setString(1, entity.getName());
                var resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    sum.set(getSalary(resultSet));
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOGGER.info("SALARY OF DEVELOPERS BY PROJECT  " + "`" + entity.getName() + "`" + " WAS CREATED");
        return sum.get();
    }


    @Override
    public List<String> getDevelopersOfProject(T entity) {
        List<String> developers = new ArrayList<>();
        String query = String.format("SELECT developers.name FROM developer_project INNER JOIN developers ON developer_project.developer_id = developers.id INNER JOIN projects ON developer_project.project_id = projects.id WHERE projects.name = ? GROUP BY developers.name");
        PgUtil.executeWithPrepareStatementQuery(query, ps -> {
            ps.setString(1, entity.getName());
//            ps.setString(2, entity.getFirst_name());
//            ps.setString(3, entity.getLast_name());

            var resultSet = ps.executeQuery();
            while (resultSet.next()) {
                developers.add(getSkillsName(resultSet));
            }
        });

        LOGGER.info("DEVELOPERS OF PROJECT " + entity.getName() + " WAS CREATED");
        return developers;

    }

    @Override
    public List<String> getJavaDevelopers() {
        List<String> developersListJava = new ArrayList<>();
        String query = "SELECT developers.name FROM developer_skills INNER JOIN developers ON developer_skills.developer_id = developers.id INNER JOIN skills ON developer_skills.skill_id = skills.id WHERE skills.branch = 'Java' GROUP BY developers.name";
        PgUtil.executeWithPrepareStatementQuery(query, ps -> {
            var resultSet = ps.executeQuery();
            while (resultSet.next()) {
                developersListJava.add(getSkillsName(resultSet));
            }
        });
        LOGGER.info("LIST OF DEVELOPERS WORKING ON \"JAVA\" WAS COMPLETED");
        return developersListJava;
    }

    @Override
    public List<String> getMiddleDevelopers() {
        List<String> developersListMiddle = new ArrayList<>();
        String query = "SELECT developers.name FROM developer_skills INNER JOIN developers ON developer_skills.developer_id = developers.id INNER JOIN skills ON developer_skills.skill_id = skills.id WHERE skills.level = 'Middle' GROUP BY developers.name";

        PgUtil.executeWithPrepareStatementQuery(query, ps -> {
            var resultSet = ps.executeQuery();
            while (resultSet.next()) {
                developersListMiddle.add(getSkillsName(resultSet));
            }
        });
        LOGGER.info("LIST OF `MIDDLE` DEVELOPERS WAS COMPLETED");
        return developersListMiddle;
    }

    public List<String> ListOfProjectByFormat() {
        List<String> listOfProjectByFormat = new ArrayList<>();
        String query = "SELECT projects.date_creation, projects.name, COUNT(developers.name) FROM developer_project INNER JOIN projects ON developer_project.project_id = projects.id INNER JOIN developers ON developer_project.developer_id = developers.id GROUP BY projects.date_creation, projects.name ";
        PgUtil.executeWithPrepareStatementQuery(query, ps -> {
            var resultSet = ps.executeQuery();
            while (resultSet.next()) {
                listOfProjectByFormat.add(getString(resultSet));
            }
        });
        LOGGER.info("THE LIST OF PROJECTS BY THE FORMAT WAS COMPLETED");
        return listOfProjectByFormat;
    }
}











