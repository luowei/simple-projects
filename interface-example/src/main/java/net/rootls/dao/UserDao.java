package net.rootls.dao;

import net.rootls.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-2-25
 * Time: 下午4:16
 * To change this template use File | Settings | File Templates.
 */
public interface UserDao {
    User findUserById(Integer userId);

    User findByUsernameAndPassword(String username, String password);

    boolean updateUserPassword(String userName, String newPassword);

    void updateLoginInfo(User user, String clientIp);
}
