package com.lesson.maven.lesson33;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class C3p0ConnectionsPool {
    private static ComboPooledDataSource pool =
            new ComboPooledDataSource();
    private C3p0ConnectionsPool(){}
    // static {
    // програмные настройки
    // pool.setUser("username");
    // и т.д
    // }

    public static Connection getConnectionFromPool() throws SQLException {
        return pool.getConnection();
    }

}
