package com.shortthirdman.core.framework.spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.List;

public class CreateDataSource {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String JDBC_URL = "jdbc:mysql://localhost/mediadb";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    public static void main(String[] args) {
        DataSource source = getDataSource();
        JdbcTemplate template = new JdbcTemplate(source);

        System.out.println("DataSource = " + template.getDataSource());
        List records = template.queryForList("SELECT * FROM records");
        for (int i = 0; i < records.size(); i++) {
            System.out.println("Records = " + records.get(i));
        }
    }

    /**
     * Returns a DataSource object for connection to the database.
     *
     * @return a DataSource.
     */
    private static DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(CreateDataSource.DRIVER);
        dataSource.setUrl(CreateDataSource.JDBC_URL);
        dataSource.setUsername(CreateDataSource.USERNAME);
        dataSource.setPassword(CreateDataSource.PASSWORD);
        return dataSource;
    }
}