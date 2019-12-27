package com.vgit.yunqiang.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.bstek.ureport.definition.datasource.BuildinDatasource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ReportDataSource implements BuildinDatasource {

    @Autowired
    private DruidDataSource dataSource;

    @Override
    public String name() {
        return "db_yunqiang";
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDataSource(DruidDataSource dataSource) {
        this.dataSource = dataSource;
    }

}
