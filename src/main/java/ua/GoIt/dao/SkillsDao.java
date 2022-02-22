package ua.GoIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.GoIt.model.Developers;
import ua.GoIt.model.Skills;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SkillsDao extends AbstractDao<Skills>{
    private static  final Logger LOGGER = LogManager.getLogger(SkillsDao.class);

    @Override
    String getTableName() {
        return "skills";
    }

    @Override
    Skills mapToEntity(ResultSet resultSet) throws SQLException {
        Skills skills= new Skills();
        skills.setId(resultSet.getLong("id"));
        skills.setBranch(resultSet.getString("branch"));
        skills.setLevel(resultSet.getString("level"));

        return skills;
    }

    @Override
    public Optional<Skills> create(Skills skills) {
        String sql = "insert into skills (branch,level) values (?,?)";


        PgUtill.executeWithPrepareStatement(sql, ps -> {
            ps.setString(1, skills.getBranch());
            ps.setString(2, skills.getLevel());
        });
        LOGGER.info(" created records ");
        return Optional.empty();
    }

    @Override
    public void update(Skills skills) {
        String sql = "update skills set level = ? where id = ?";

        PgUtill.executeWithPrepareStatement(sql, ps -> {
            ps.setString(1, skills.getLevel());


            ps.setLong(2, skills.getId());
        });
        LOGGER.info(" updated records ");
    }
}
