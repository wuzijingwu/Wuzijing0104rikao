package text.bwei.com.wuzijing0104rikao;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import text.bwei.com.wuzijing0104rikao.gen.UserDao;

import static text.bwei.com.wuzijing0104rikao.App.userDao;

/**
 * Created by dell on 2018/1/4.
 */

public class DownlaodSqlTool {
    /**
     * 创建下载的具体信息
     */
    public void insertInfos(List<DownloadInfo> infos) {
        for (DownloadInfo info : infos) {
            User user = new User(null, info.getThreadId(), info.getStartPos(), info.getEndPos(), info.getCompeleteSize(), info.getUrl());
            userDao.insert(user);
        }
    }

    /**
     * 得到下载具体信息
     */
    public List<DownloadInfo> getInfos(String urlstr) {
        List<DownloadInfo> list = new ArrayList<DownloadInfo>();
        List<User> list1 = userDao.queryBuilder().where(UserDao.Properties.Url.eq(urlstr)).build().list();
        for (User user : list1) {
            DownloadInfo infoss = new DownloadInfo(
                    user.getThread_id(), user.getStart_pos(), user.getEnd_pos(),
                    user.getCompelete_size(), user.getUrl());
            Log.d("main-----", infoss.toString());
            list.add(infoss);
        }

        return list;
    }

    /**
     * 更新数据库中的下载信息
     */
    public void updataInfos(int threadId, int compeleteSize, String urlstr) {
        User user = userDao.queryBuilder()
                .where(UserDao.Properties.Thread_id.eq(threadId), UserDao.Properties.Url.eq(urlstr)).build().unique();
        user.setCompelete_size(compeleteSize);
        userDao.update(user);
    }

    /**
     * 下载完成后删除数据库中的数据
     */
    public void delete(String url) {
        userDao.deleteAll();
    }

}