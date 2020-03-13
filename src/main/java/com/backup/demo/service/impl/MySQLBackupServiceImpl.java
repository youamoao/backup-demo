package com.backup.demo.service.impl;

import com.backup.demo.service.MySQLBackupService;
import com.backup.demo.utils.MySQLBackupRestoreUtils;
import org.springframework.stereotype.Service;

/**
 * @author WCS.Wang
 * @version V1.0
 * @Package com.backup.demo.service.impl
 * @Name backup-demo
 * @Description: TODO (用一句话描述该文件做什么)
 * @date 2020-01-30
 */
@Service
public class MySQLBackupServiceImpl implements MySQLBackupService {


    @Override
    public boolean backup(String host, String userName, String password, String backupFolderPath, String fileName, String database) throws Exception {
        return MySQLBackupRestoreUtils.backup(host,userName,password,backupFolderPath,fileName,database);
    }

    @Override
    public boolean restore(String restoreFilePath, String host, String userName, String password, String database) throws Exception {
        return MySQLBackupRestoreUtils.restore(restoreFilePath, host, userName,password,database);
    }
}
