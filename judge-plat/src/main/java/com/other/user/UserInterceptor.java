package com.other.user;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-4-12
 * Time: 上午9:50
 * To change this template use File | Settings | File Templates.
 */
public class UserInterceptor extends HandlerInterceptorAdapter {

    private static Log log = LogFactory.getLog(UserInterceptor.class);

    private UserRepository userRepository;
    private String loginUrl = "/login.jsp";

    @Resource
    public void setUserRepository(UserRepository adminRepository) {
        this.userRepository = adminRepository;
    }

    public UserInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        if(true)
//            return true;

        UserInfo user = (UserInfo) request.getSession().getAttribute("gujiauser");

        if (user == null) {
            //取cookie
            user = getUserFromCookie(request);

            if (user != null) {
                //从cookie中取了用户
                user.setTrial(null);

                //查数据库
                UserInfo userDB = userRepository.findUserByUser(user);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String toDayStr = sdf.format(new Date());

                //验证正式与是否过期
                if (userDB != null && userDB.getTrial() != null && userDB.getTrial().equals(0)
                        && userDB.getEndTime() != null && sdf.format(userDB.getEndTime()).compareTo(toDayStr) >= 0 ) {
                    user.setTrial(userDB.getTrial());
                    user.setQymc(userDB.getQymc());
                    user.setUsername(userDB.getUsername());
                }
                //只有在cookie中取了用户才设置session
                request.getSession().setAttribute("gujiauser", user);
            }

        }
        return super.preHandle(request, response, handler);
    }

    private UserInfo getUserFromCookie(HttpServletRequest request) throws UnsupportedEncodingException {
        Cookie cookies[] = request.getCookies();
        String userId = null;
        String username = null;
        String password = null;
        String parentid = null;

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("userid")) {
                    userId = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
                }
                if (cookies[i].getName().equals("username")) {
                    username = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
                }
                if (cookies[i].getName().equals("password")) {
                    password = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
                    password = transactSQLInjection(password);
                }
                if (cookies[i].getName().equals("parentid")) {
                    parentid = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
                }
            }
        }

        if (userId == null || !isNumeric(userId)) {
            return null;
        }

        return new UserInfo(Integer.valueOf(userId), username, password, Integer.valueOf(parentid));
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    private UserInfo getUserFormCookies(HttpServletRequest request) {
        return null;
    }

    public String transactSQLInjection(String str) {
        if (str == null || str.equals("")) {
            return "";
        }
        return str.replaceAll(".*([';]+|(--)+).*", " ");
    }

    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


}
