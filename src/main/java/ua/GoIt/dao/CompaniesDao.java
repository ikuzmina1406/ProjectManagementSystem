package ua.GoIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.GoIt.model.Companies;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CompaniesDao extends AbstractDao<Companies> {

    private static final Logger LOGGER = LogManager.getLogger(CompaniesDao.class);

    @Override
    Companies mapToEntity(ResultSet resultSet) throws SQLException {
        Companies companies = new Companies();
        companies.setId(resultSet.getLong("id"));
        companies.setName(resultSet.getString("name"));
        companies.setState_code(resultSet.getString("state_code"));
        companies.setCountry(resultSet.getString("country"));
        companies.setInfo(resultSet.getString("info"));

        return companies;
    }

    @Override
    public Optional<Companies> create(Companies companies) {

        String sql = "INSERT INTO companies(name, state_code, country, info) VALUES (?,?,?,?)";


        PgUtil.executeWithPrepareStatement(sql, ps -> {
            ps.setString(1, companies.getName());
            ps.setString(2, companies.getState_code());
            ps.setString(3, companies.getCountry());
            ps.setString(4, companies.getInfo());

        });
        LOGGER.info(" CREATED RECORDS ");
        return Optional.empty();
    }

    @Override
    public void update(Companies companies) {
        String sql = "UPDATE companies SET name = ?, state_code =? , country = ?, info =? WHERE id = ?";

        PgUtil.executeWithPrepareStatement(sql, ps -> {
            ps.setString(1, companies.getName());
            ps.setString(2, companies.getState_code());
            ps.setString(3, companies.getCountry());
            ps.setString(4, companies.getInfo());
            ps.setLong(5, companies.getId());
        });
        LOGGER.info(" UPDATED RECORDS ");
    }

    @Override
    String getTableName() {
        return "companies";
    }

}
