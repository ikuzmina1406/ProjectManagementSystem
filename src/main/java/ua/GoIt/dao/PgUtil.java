package ua.GoIt.dao;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.GoIt.config.DataSourceHolder;


import java.sql.*;


public class PgUtil {

    private static final Logger LOGGER = LogManager.getLogger(PgUtil.class);

    public static int executeWithPrepareStatement(String sql, ParamSetter psCall) {

        Connection connection;
        try {
            connection = DataSourceHolder.getDataSource().getConnection();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                psCall.set(ps);
                return ps.executeUpdate();


            } catch (SQLException throwables) {
                LOGGER.error("EXCEPTION while tying to execute SQL request");
                return 0;
            }
        } catch (SQLException throwables) {
            LOGGER.error("EXCEPTION while tying to execute SQL request");
        }
        return  0;
    }
    public static Object executeWithPrepareStatementQuery(String sql, ParamSetter psCall) {

        Connection connection;
        try {
            connection = DataSourceHolder.getDataSource().getConnection();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                psCall.set(ps);
                return ps.executeQuery();


            } catch (SQLException throwables) {
                LOGGER.error("EXCEPTION while tying to execute SQL request");
                return 0;
            }
        } catch (SQLException throwables) {
            LOGGER.error("EXCEPTION while tying to execute SQL request");
        }
        return  0;
    }

    public static ResultSet getWithPrepareStatement(String sql, ParamSetter psCall) throws SQLException {
        Connection connection;
        connection = DataSourceHolder.getDataSource().getConnection();

        PreparedStatement ps = connection.prepareStatement(sql);
        {
            psCall.set(ps);
            return ps.executeQuery();

        }
    }



    @FunctionalInterface
    public interface ParamSetter {
        void set(PreparedStatement ps) throws SQLException;
    }
}


