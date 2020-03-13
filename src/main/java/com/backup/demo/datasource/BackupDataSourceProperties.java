package com.backup.demo.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author WCS.Wang
 * @version V1.0
 * @Package com.backup.demo.datasource
 * @Name backup-demo
 * @Description: TODO (用一句话描述该文件做什么)
 * @date 2020-01-30
 */
@Component
@ConfigurationProperties(prefix = "backup.datasource")
public class BackupDataSourceProperties {

    private String host;
    private String userName;
    private String password;
    private String database;
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDatabase() {
        return database;
    }
    public void setDatabase(String database) {
        this.database = database;
    }

}
