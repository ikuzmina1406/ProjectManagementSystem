package ua.GoIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.GoIt.model.Developers;
import ua.GoIt.model.Projects;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ProjectsDao extends AbstractDao<Projects>{
    private static  final Logger LOGGER = LogManager.getLogger(ProjectsDao.class);

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
        return projects;
    }

    @Override
    public Optional<Projects> create(Projects projects) {
        String sql = "insert into projects(name,info,status,cost) values (?,?,?,?)";


        PgUtill.executeWithPrepareStatement(sql, ps -> {
            ps.setString(1, projects.getName());
            ps.setString(2, projects.getInfo());
            ps.setInt(3, projects.getStatus());
            ps.setBigDecimal(4, projects.getCost());
        });
        LOGGER.info(" created records ");
        return Optional.empty();
    }


    @Override

    public void update(Projects projects) {
        String sql = "update projects set name = ? where id = ?";

        PgUtill.executeWithPrepareStatement(sql, ps -> {
            ps.setString(1, projects.getName());


            ps.setLong(2, projects.getId());
        });
        LOGGER.info(" updated records ");

    }
}
