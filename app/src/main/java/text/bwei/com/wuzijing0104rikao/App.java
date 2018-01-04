package text.bwei.com.wuzijing0104rikao;

import android.app.Application;

import text.bwei.com.wuzijing0104rikao.gen.DaoMaster;
import text.bwei.com.wuzijing0104rikao.gen.DaoSession;
import text.bwei.com.wuzijing0104rikao.gen.UserDao;

/**
 * Created by dell on 2018/1/4.
 */

public class App extends Application {
    public static UserDao userDao;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "lenvess.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
    }
}
