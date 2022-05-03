package ua.GoIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.GoIt.model.Projects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ProjectsDao extends AbstractDao<Projects> {
    private static final Logger LOGGER = LogManager.getLogger(ProjectsDao.class);


    @Override
    String getTableName() {
        return "projects";
    }

    @Override
    Projects mapToEntity(ResultSet resultSet) throws SQLException {
        Projects projects = new Projects();
        projects.setId(resultSet.getLong("id"));
        projects.setName(resultSet.getString("name"));
        projects.setInfo(resultSet.getString("info"));
        projects.setStatus(resultSet.getInt("status"));
        projects.setCost(resultSet.getBigDecimal("cost"));
        projects.setDate_creation(resultSet.getDate("date_creation"));
        return projects;
    }


    @Override
    public Optional<Projects> create(Projects projects) {
        String sql = "INSERT INTO projects(name,info,status,cost,date_creation) VALUES (?,?,?,?,?)";


        PgUtil.executeWithPrepareStatement(sql, ps -> {
            ps.setString(1, projects.getName());
            ps.setString(2, projects.getInfo());
            ps.setInt(3, projects.getStatus());
            ps.setBigDecimal(4, projects.getCost());
            ps.setDate(5, projects.getDate_creation());
        });
        LOGGER.info(" CREATED RECORDS ");
        return Optional.empty();
    }


    @Override

    public void update(Projects projects) {
        String sql = "UPDATE projects SET name =?, info =?, status = ?, cost =?, date_creation = ? WHERE id = ?";

        PgUtil.executeWithPrepareStatement(sql, ps -> {
            ps.setString(1, projects.getName());
            ps.setString(2, projects.getInfo());
            ps.setInt(3, projects.getStatus());
            ps.setBigDecimal(4, projects.getCost());
            ps.setDate(5, projects.getDate_creation());
            ps.setLong(6, projects.getId());
        });
        LOGGER.info(" CREATED RECORDS ");

    }


}
