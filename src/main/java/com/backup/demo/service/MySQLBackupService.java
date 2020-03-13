package com.backup.demo.service;

/**
 * @author WCS.Wang
 * @version V1.0
 * @Package com.backup.demo.service
 * @Name backup-demo
 * @Description: TODO (用一句话描述该文件做什么)
 * @date 2020-01-30
 */
public interface MySQLBackupService {

    /**
     *
     * 备份数据库
     * @param host host地址，可以是本地或远程
     * @param userName 数据库的用户名
     * @param password 数据库的密码
     * @param backupFolderPath 备份的路径
     * @param fileName 备份的文件名
     * @param database 需要备份的数据库名称
     * @return
     * @throws Exception
     */
    boolean backup(String host, String userName, String password, String backupFolderPath, String fileName, String database) throws Exception;

    /**
     * 恢复数据库
     * @param restoreFilePath 恢复文件路径
     * @param host host地址，可以是本地或远程
     * @param userName 数据库的用户名
     * @param password 数据库的密码
     * @param database 需要恢复的数据库名称
     * @return
     * @throws Exception
     */
    boolean restore(String restoreFilePath, String host, String userName, String password, String database) throws Exception;
}
