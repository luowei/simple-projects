package net.xxxx.service.impl;

import net.xxxx.dao.UserDao;
import net.xxxx.model.User;
import net.xxxx.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-2-22
 * Time: 上午10:04
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public User getCurrentUser(Integer userId) {
        return userDao.findUserById(userId);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userDao.findByUsernameAndPassword(username,password);
    }

    @Override
    public boolean updateUserPassword(String userName, String newPassword) {
        return userDao.updateUserPassword(userName,newPassword);
    }

    @Override
    public void updateLoginInfo(User user, String clientIp) {
        userDao.updateLoginInfo(user, clientIp);
    }
}
