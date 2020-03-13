package com.backup.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

/**
 * @author WCS.Wang
 * @version V1.0
 * @Package com.backup.demo.utils
 * @Name backup-demo
 * @Description: TODO (用一句话描述该文件做什么)
 * @date 2020-01-30
 */
@Slf4j
public class MySQLBackupRestoreUtils {

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
    public static boolean backup(String host, String userName, String password, String backupFolderPath, String fileName, String database) throws Exception {
        File backupFolderFile = new File(backupFolderPath);
        if(!backupFolderFile.exists()) {
            // 如果目录不存在则创建
            backupFolderFile.mkdirs();
        }
        if(!backupFolderPath.endsWith(File.separator) && !backupFolderPath.endsWith("/")) {
            backupFolderPath = backupFolderPath + File.separator;
        }
        // 拼接执行命令
        String backupFilePath = backupFolderPath + fileName;

        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("mysqldump --opt ").append(" --add-drop-database ").append(" --add-drop-table ");
        stringBuilder.append("mysqldump ");
        stringBuilder.append(" -h").append(host).append(" -u").append(userName).append(" -p").append(password);
        stringBuilder.append(" --result-file=").append(backupFilePath).append(" --default-character-set=utf8 ").append(database);

        // 调用外部执行 exe文件的Java API
        Process process = Runtime.getRuntime().exec(getCommand(stringBuilder.toString()));
        log.error("输出值：" + process.waitFor());
        log.error("输出值：" + process);
        if(process.waitFor() == 0) {
            // 0 表示线程正常终止
            log.info("数据完成备份，备份至 " + backupFilePath + " 文件中。");
            return true;
        }
        return  false;
    }

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
    public static boolean restore(String restoreFilePath, String host, String userName, String password, String database) throws Exception {
        File restoreFile = new File(restoreFilePath);
        if(restoreFile.isDirectory()) {
            for (File file : restoreFile.listFiles()) {
                if(file.exists() && file.getPath().endsWith(".sql")) {
                    restoreFilePath = file.getAbsolutePath();
                    break;
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mysql -h").append(host).append(" -u").append(userName).append(" -p").append(password);
        stringBuilder.append(" ").append(database).append(" < ").append(restoreFilePath);
        try {
            Process process = Runtime.getRuntime().exec(getCommand(stringBuilder.toString()));
            if(process.waitFor() == 0) {
                log.info("数据已从 " + restoreFilePath + " 导入至数据库中。");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return  false;
        }
        return true;
    }

    private static String[] getCommand(String command) {
        String os = System.getProperty("os.name");
        String shell = "/bin/bash";
        String c = "-c";
        if(os.toLowerCase().startsWith("win")){
            shell = "cmd";
            c = "/c";
        }
        String[] cmd = { shell, c, command };
        return cmd;
    }

}
