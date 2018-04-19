package com.demo.healthy;

import android.os.Environment;
import org.xutils.DbManager;
import org.xutils.db.table.TableEntity;
import org.xutils.x;

import java.io.File;


/**
 * 数据库操作类
 * 包含数据的增删改查
 *
 * @auth Mr liu
 * @date 2018-01-19 11:00:59
 * @copyright HGC
 */

public class HgcDb {

    private final DbManager db;

    private static class SingelTon {
        public static HgcDb INSTANCE = new HgcDb();
    }

    public static HgcDb getInstance() {
        return SingelTon.INSTANCE;
    }

    private final String DB_NAME = "sbw.db";

    private HgcDb() {
        //数据库文件
        String s = Environment.getExternalStorageDirectory() + File.separator + "sbw";
        File file = new File(s);
        //判断文件在不在
        if (!file.exists()) {
            file.mkdirs();
        }
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig();
        daoConfig.setDbDir(file).setAllowTransaction(true).setDbName(DB_NAME)
                .setDbVersion(1).setDbOpenListener(new DbManager.DbOpenListener() {
            @Override
            public void onDbOpened(DbManager db) {

            }
        }).setDbUpgradeListener(new DbManager.DbUpgradeListener() {
            @Override
            public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

            }
        }).setTableCreateListener(new DbManager.TableCreateListener() {
            @Override
            public void onTableCreated(DbManager db, TableEntity<?> table) {
                System.out.println("db = [" + db + "], table = [" + table.getName() + "]");
            }
        });
        db = x.getDb(daoConfig);

    }

    /**
     * 获取数据库实例
     * @return
     */
    public DbManager getDb() {
        return db;
    }
}




