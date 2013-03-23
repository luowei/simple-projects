package net.rootls.service;

import net.rootls.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-2-22
 * Time: 上午10:02
 * To change this template use File | Settings | File Templates.
 */
public interface UserService{
    User getCurrentUser(Integer userId);

    User findByUsernameAndPassword(String username, String password);

    boolean updateUserPassword(String userName, String newPassword);

    void updateLoginInfo(User user, String clientIp);
}
