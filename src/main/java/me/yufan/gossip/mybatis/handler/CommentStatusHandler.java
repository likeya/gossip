package me.yufan.gossip.mybatis.handler;

import me.yufan.gossip.mybatis.entity.enums.CommentStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentStatusHandler extends BaseTypeHandler<CommentStatus> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, CommentStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getStatus());
    }

    @Override
    public CommentStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return CommentStatus.convert(rs.getString(columnName));
    }

    @Override
    public CommentStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
           return CommentStatus.convert(rs.getString(columnIndex));
    }

    @Override
    public CommentStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return CommentStatus.convert(cs.getString(columnIndex));
    }
}
