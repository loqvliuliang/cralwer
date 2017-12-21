package com.example.demo.mapper.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by 刘亮 on 2017/12/21.
 */

@MappedTypes(ZonedDateTime.class)
public class ZonedDateTimeTypeHandler extends BaseTypeHandler<ZonedDateTime> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, ZonedDateTime zonedDateTime, JdbcType jdbcType) throws SQLException {
        preparedStatement.setTimestamp(
                i,
                Timestamp.from(zonedDateTime.toInstant()),
                GregorianCalendar.from(zonedDateTime)
        );
    }

    @Override
    public ZonedDateTime getNullableResult(ResultSet resultSet, String s) throws SQLException {
        Timestamp ts = resultSet.getTimestamp(s, Calendar.getInstance());
        if (ts != null) {
            return ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault());
        }
        return null;
    }

    @Override
    public ZonedDateTime getNullableResult(ResultSet resultSet, int i) throws SQLException {
        Timestamp ts = resultSet.getTimestamp(i, Calendar.getInstance());
        if (ts != null) {
            return ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault());
        }
        return null;
    }

    @Override
    public ZonedDateTime getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        Timestamp ts = callableStatement.getTimestamp(i, Calendar.getInstance());
        if (ts != null) {
            return ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault());
        }
        return null;
    }
}