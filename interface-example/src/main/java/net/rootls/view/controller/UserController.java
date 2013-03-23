package net.rootls.view.controller;

import net.rootls.bean.NeedLogin;
import net.rootls.model.User;
import net.rootls.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

import static java.lang.String.format;
import static net.rootls.bean.Config.JSON_MSG;
import static net.rootls.bean.Config.loginCache;
import static net.rootls.utils.Md5Util.generatePassword;

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

    @NeedLogin(false) @ResponseBody
    @RequestMapping("/nodata")
    public String nodata() {
        return format(JSON_MSG, 0, "no data !");
    }

    @NeedLogin(false) @ResponseBody
    @RequestMapping("/toofrequent")
    public String toofrequent() {
        return format(JSON_MSG, 0, "you request too frequently !");
    }

    @NeedLogin(false) @RequestMapping("/clearLoginCache")
    public String clearLoginCache() {
        loginCache.clear();
        return format(JSON_MSG, 1, "Clear Login Cache Success !");
    }

    @ResponseBody @RequestMapping("/updatePassword")
    public String updatePassword(Model model, User user, String newPassword) {
        if(StringUtils.isBlank(newPassword)){
            return format(JSON_MSG, 1, "password cann't empty !");
        }

        boolean success = userService.updateUserPassword(user.getUsername(), generatePassword(newPassword.trim()));
        if (success) {
            return format(JSON_MSG, 1, "update " + user.getUsername() + "'s password success !");
        } else {
            return format(JSON_MSG, 0, "update " + user.getUsername() + "'s password faild !");
        }
    }

    @NeedLogin(false) @RequestMapping("/login")
    public String login(String username,String password,String type,
                        HttpSession session,RedirectAttributes redirectAttrs){
        session.setAttribute("user",userService
                .findByUsernameAndPassword(username,generatePassword(password)));
        if("toProductCode".equals(type)){
            redirectAttrs.addFlashAttribute("username",username)
                .addFlashAttribute("password",password);
            return "redirect:/trade/listpcode.do";
        }
        return "index";
    }

    @ResponseBody @RequestMapping("/logout")
    public String logout(SessionStatus sessionStatus){
        if(!sessionStatus.isComplete()){
            sessionStatus.setComplete();
        }
        return format(JSON_MSG, 1, "logout success !");
    }

}
