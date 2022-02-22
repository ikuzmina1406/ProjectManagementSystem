package ua.GoIt.dao;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.GoIt.config.DataSourceHolder;

import java.sql.*;

public class PgUtill {

    private static final Logger LOGGER = LogManager.getLogger(PgUtill.class);

    public static int executeWithPrepareStatement(String sql, ParamSetter psCall) {
//        Connection connection ;
//        try {
//            connection = DataSourceHolder.getDataSource().getConnection();
//            try (PreparedStatement ps = connection.prepareStatement(sql)) {
//                psCall.set(ps);
//                return ps.executeUpdate();
//
//        } catch (SQLException throwables) {
//                throwables.printStackTrace();
//                return 0;
//            }
//        } catch (SQLException throwable) {
//            throwable.printStackTrace();
//
//        }
//        return 0;
//    }
//
//
//        try {
//            connection = DataSourceHolder.getDataSource().getConnection();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            psCall.set(ps);
//            return ps.executeUpdate();
//
//        } catch (SQLException throwable) {
//            throwable.printStackTrace();
//            return 0;
//        }
//    }
//        Connection connection;
//        try {
//            connection = DataSourceHolder.getDataSource().getConnection();
//            PreparedStatement ps = connection.prepareStatement(sql);
//            psCall.set(ps);
//            return ps.executeUpdate();
//
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            return 0;
//        }
//    }
//        Connection connection;
//        try {
//            connection = DataSourceHolder.getDataSource().getConnection();
//            try (PreparedStatement ps = connection.prepareStatement(sql)) {
//                psCall.set(ps);
//                return ps.executeUpdate();
//
//
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//                return 0;
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return  0;
//    }
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
//        try (Connection connection = DataSourceHolder.getDataSource().getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//            psCall.set(ps);
//            return ps.executeUpdate();
//
//
//        } catch (SQLException e) {
//            LOGGER.error("exception while tying to execute SQL request", e);
//            return 0;
//        }
//
//    }

    //   }

    //    public static ResultSet getWithPrepareStatement(String sql, ParamSetter psCall) throws SQLException {
//        Connection connection;
//        connection = DataSourceHolder.getDataSource().getConnection();
//        try {
//
//            PreparedStatement ps = connection.prepareStatement(sql);
//            psCall.set(ps);
//            return ps.executeQuery();
//
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            return null;
//        }
//
//    }
    public static ResultSet getWithPrepareStatement(String sql, ParamSetter psCall) throws SQLException {
        Connection connection;
        connection = DataSourceHolder.getDataSource().getConnection();
////            try
////
////                ( PreparedStatement ps = connection.prepareStatement(sql)){
////                psCall.set(ps);
////                return ps.executeQuery();
////            }
        PreparedStatement ps = connection.prepareStatement(sql);
        {
            psCall.set(ps);
            return ps.executeQuery();

        }
    }
//        try (Connection connection = DataSourceHolder.getDataSource().getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//            psCall.set(ps);
//            return ps.executeQuery();
//
//        }
//    }


    @FunctionalInterface
    public interface ParamSetter {
        void set(PreparedStatement ps) throws SQLException;
    }
}


