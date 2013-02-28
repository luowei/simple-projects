package net.xxxx.view.controller;

import net.xxxx.bean.NeedLogin;
import net.xxxx.model.User;
import net.xxxx.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import static java.lang.String.format;
import static net.xxxx.bean.Config.JSON_MSG;
import static net.xxxx.utils.Md5Util.generatePassword;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-2-25
 * Time: 下午2:52
 * To change this template use File | Settings | File Templates.
 */
@Controller
@NeedLogin
@SessionAttributes("user")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @NeedLogin(false)
    @ResponseBody
    @RequestMapping("/nologin")
    public String nologin() {
        return format(JSON_MSG, 0, "login faild !");
    }

    @NeedLogin(false)
    @ResponseBody
    @RequestMapping("/nodata")
    public String nodata() {
        return format(JSON_MSG, 0, "no data !");
    }

    @ResponseBody @RequestMapping("/updatePassword")
    public String updatePassword(Model model, User user, String newPassword) {
        if(StringUtils.isBlank(newPassword)){
            return format(JSON_MSG, 1, "password cann't empty !");
        }

        boolean success = userService.updateUserPassword(user.getUserName(), generatePassword(newPassword.trim()));
        if (success) {
            return format(JSON_MSG, 1, "update " + user.getUserName() + "'s password success !");
        } else {
            return format(JSON_MSG, 0, "update " + user.getUserName() + "'s password faild !");
        }
    }

    @ResponseBody @RequestMapping("/logout")
    public String logout(SessionStatus sessionStatus){
        if(!sessionStatus.isComplete()){
            sessionStatus.setComplete();
        }
        return format(JSON_MSG, 1, "logout success !");
    }

}
