package me.yufan.gossip.mybatis.handler;

import me.yufan.gossip.mybatis.entity.enums.AuthorType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorTypeHandler extends BaseTypeHandler<AuthorType> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, AuthorType parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public AuthorType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return AuthorType.convert(rs.getInt(columnName));
    }

    @Override
    public AuthorType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return AuthorType.convert(rs.getInt(columnIndex));
    }

    @Override
    public AuthorType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return AuthorType.convert(cs.getInt(columnIndex));
    }
}
