package com.shortthirdman.core.framework.spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.Date;

public class InsertRecord {
    private static final String sql =
            "INSERT INTO records (title, " +
            "    release_date, " +
            "    artist_id, " +
            "    label_id, " +
            "    created) " +
            "VALUES (?, ?, ?, ?, ?)";

    private DataSource dataSource;

    public InsertDemo(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveRecord(String title, Date releaseDate, Integer artistId, Integer labelId) {
        JdbcTemplate template = new JdbcTemplate(this.dataSource);

        Object[] params = new Object[] {
                title, releaseDate, artistId, labelId, new Date()
        };
        int[] types = new int[] {
                Types.VARCHAR,
                Types.DATE,
                Types.INTEGER,
                Types.INTEGER,
                Types.DATE
        };
        int row = template.update(sql, params, types);
        System.out.println(row + " row inserted.");
    }

    public static DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/mediadb");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }

    public static void main(String[] args) {
        DataSource dataSource = getDataSource();

        InsertRecord demo = new InsertRecord(dataSource);
        demo.saveRecord("Rock Beatles", new Date(), 1, 1);
    }
}