package ua.GoIt.dao;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.GoIt.config.DataSourceHolder;
import ua.GoIt.exeptions.SqlReturnedItemException;

import java.sql.*;
import java.util.Optional;

//public class PgUtill {
//
//    private static final Logger LOGGER = LogManager.getLogger(PgUtill.class);
//
//    public static int executeWithPrepareStatement(String sql, ParameterSetter psCall) {
//
//        try (Connection connection = DataSourceHolder.getDataSource().getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//            psCall.set(ps);
//            return ps.executeUpdate();
//        } catch (SQLException e) {
//            LOGGER.error("Exception while trying do SQL request", e);
//            return 0;
//        }
//    }
//
//    public static Optional<Long> executePrepareStatementAndGetId(String sql, ParameterSetter psCall) throws SqlReturnedItemException {
//        try (Connection connection = DataSourceHolder.getDataSource().getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//            psCall.set(ps);
//            ps.executeUpdate();
//            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    return Optional.of(generatedKeys.getLong(1));
//                } else {
//                    throw new SqlReturnedItemException("Execution failed, no one entity was returned");
//                }
//            }
//        } catch (SQLException e) {
//            LOGGER.error("Exception while trying do SQL request", e);
//        }
//        return Optional.empty();
//    }
//
//    public static ResultSet getWithPrepareStatement(String sql, ParameterSetter psCall) throws SQLException {
//        try (Connection connection = DataSourceHolder.getDataSource().getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//            psCall.set(ps);
//            return ps.executeQuery();
//        }
//    }
//
//
//    @FunctionalInterface
//    public interface ParameterSetter {
//        void set(PreparedStatement ps) throws SQLException;
//    }
//}
//

//
//
public class PgUtill {

    private static final Logger LOGGER = LogManager.getLogger(PgUtill.class);

    public static int executeWithPrepareStatement(String sql, ParamSetter psCall) {

        Connection connection;
        try {
            connection = DataSourceHolder.getDataSource().getConnection();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                psCall.set(ps);
                return ps.executeUpdate();


            } catch (SQLException throwables) {
                LOGGER.error("exception while tying to execute SQL request");
                return 0;
            }
        } catch (SQLException throwables) {
            LOGGER.error("exception while tying to execute SQL request");
        }
        return  0;
    }

    public static Optional<Long> executePreparedStatementAndGetId(String sql, ParamSetter psCall) throws SqlReturnedItemException {
        try (Connection connection = DataSourceHolder.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            psCall.set(ps);
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return Optional.of(generatedKeys.getLong(1));
                } else {
                    throw new SqlReturnedItemException("Execution failed, no one entity was returned");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Exception while trying do SQL request", e);
        }
        return Optional.empty();
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


