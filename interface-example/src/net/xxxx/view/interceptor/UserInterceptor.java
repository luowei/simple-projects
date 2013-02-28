package net.xxxx.view.interceptor;

import net.xxxx.bean.NeedLogin;
import net.xxxx.model.User;
import net.xxxx.service.UserService;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static net.xxxx.bean.Config.SESSION_MAX_INTERVAL;
import static net.xxxx.utils.IPMacUtil.validBindIp;
import static net.xxxx.utils.Md5Util.generatePassword;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-2-20
 * Time: 上午11:15
 * To change this template use File | Settings | File Templates.
 */
public class UserInterceptor extends HandlerInterceptorAdapter {
    private UserService userService;

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Method method = ((HandlerMethod) handler).getMethod();
        boolean needLogin = hasLoginAnotation(method.getDeclaringClass(), method);

        boolean result = false;
        //是否需要登录
        if (!needLogin) {
            result = true;
        } else {
            //判断用户登陆
            if (checkUserLogin(request)) {
                result = true;
            } else {
                result = false;
            }
        }
        if (!result) {
            request.getRequestDispatcher("/user/nologin.do").forward(request, response);
        }
        return result;
    }

    private boolean hasLoginAnotation(Class clazz, Method method) {

        NeedLogin methodAnnotation = method.getAnnotation(NeedLogin.class);
        if (method.isAnnotationPresent(NeedLogin.class)) {
            return methodAnnotation.value();
        }

        Annotation clazzAnnotation = clazz.getAnnotation(NeedLogin.class);
        if (clazz.isAnnotationPresent(NeedLogin.class)) {
            return ((NeedLogin) clazzAnnotation).value();
        } else {
            return false;
        }
    }

    /**
     * 检查用户登录
     *
     * @param request
     * @return
     */
    private boolean checkUserLogin(HttpServletRequest request) {

        String username = request.getAttribute("username") == null ? null : String.valueOf(request.getAttribute("username"));
        username = (username == null ? request.getParameter("username") : username);
        String password = request.getAttribute("password") == null ? null : String.valueOf(request.getAttribute("password"));
        password = (password == null ? request.getParameter("password") : password);

        boolean okUser = false;
        User user = null;
        String clientIp = null;

        if (isNotBlank(username) || isNotBlank(password)) {
            Object userObj = request.getSession().getAttribute("user");
//            Object userObj = null;
            String secretPass = generatePassword(password);
            user = userObj == null ? userService.findByUsernameAndPassword(username.replace("'", ""),secretPass ) : (User) userObj;
            clientIp = getIpAddr(request);
            okUser = user == null ? false : checkIP(clientIp, user.getIpRight()) && user.getPriceFlag() != null && user.getPriceFlag().equals(1);
        }

        if (okUser) {
            request.getSession().setMaxInactiveInterval(SESSION_MAX_INTERVAL);
            request.getSession().setAttribute("user", user);
            userService.updateLoginInfo(user, clientIp);
            return true;
        } else {
            request.getSession().setAttribute("user", null);
//             request.getSession().invalidate();
            return false;
        }
    }

    /**
     * 检查ip地址
     *
     * @param clientIp
     * @param ipRight
     * @return
     */
    private Boolean newCheckIP(String clientIp, String ipRight) {
        String bindIps[] =  ipRight.replaceAll("\\r\\n","|").split("\\|");
        return validBindIp(bindIps, clientIp);
    }


    /**
     * 检查ip地址(老方法)
     *
     * @param clientIp
     * @param ipRight
     * @return
     */
    private boolean checkIP(String clientIp, String ipRight) {
        if (ipRight == null) {
            ipRight = "";
        }
        if (ipRight.equals("*.*.*.*")) {
            return true;
        }
        boolean isOk = false;

        String array[] =  ipRight.replaceAll("\\r\\n","|").split("\\|");
        for (int i = 0; i < array.length; i++) {
            isOk = true;
            String[] clientIP = clientIp.split("[.]");
            String[] checkIP = array[i].split("[.]");
            if (!(clientIP[0].equals(checkIP[0])) && checkIP[0].indexOf("*") == -1) {
                isOk = false;
            }
            if (!(clientIP[1].equals(checkIP[1])) && checkIP[1].indexOf("*") == -1) {
                isOk = false;
            }
            if (!(clientIP[2].equals(checkIP[2])) && checkIP[2].indexOf("*") == -1) {
                isOk = false;
            }
            if (!(clientIP[3].equals(checkIP[3])) && checkIP[3].indexOf("*") == -1) {
                isOk = false;
            }
            if (isOk) {
                return isOk;
            }
        }
        return isOk;
    }

    private String getIpAddr(HttpServletRequest request/* ,@RequestHeader MultiValueMap<String,String> headers*/) {
        String ipAddress = request.getHeader("x-forwarded-for");
        ipAddress = isNullIP(ipAddress) ? request.getHeader("Proxy-Client-IP") : ipAddress;
        ipAddress = isNullIP(ipAddress) ? request.getHeader("WL-Proxy-Client-IP") : ipAddress;
        ipAddress = isNullIP(ipAddress) ? request.getHeader("WL-Proxy-Client-IP") : ipAddress;
        ipAddress = isNullIP(ipAddress) ? request.getRemoteAddr() : ipAddress;

        if (ipAddress != null && ipAddress.indexOf(".") == -1) {
            return null;

            //"***.***.***.***".length() = 15
        } else if (ipAddress != null && ipAddress.length() > 15 && ipAddress.indexOf(",") > 0) {

            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            String[] temparyip = ipAddress.split(",");
            for (int i = 0; i < temparyip.length; i++) {
                if (isIPAddress(temparyip[i])
                        && temparyip[i] != "127.0.0.1"
                        && temparyip[i].substring(0, 3) != "10."
                        && temparyip[i].substring(0, 8) != "192.168."
                        && temparyip[i].substring(0, 7) != "172.16."
                        && temparyip[i].substring(0, 8) != "169.254.") {
                    ipAddress = temparyip[i];
                }
            }
            // ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
        }
        return ipAddress;
    }

    private boolean isNullIP(String ipAddress) {
        return ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress);
    }

    private static boolean isIPAddress(String str1) {
        if (str1 == null || str1.trim().length() < 7 || str1.trim().length() > 15) {
            return false;
        } else {
            return true;
        }
    }
}

