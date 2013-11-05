package com.other.survey;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-10-31
 * Time: 下午2:52
 * To change this template use File | Settings | File Templates.
 */
@Controller

public class SurveyControler {

    @Resource
    SurveyRepository surveyRepository;

//    @RequestMapping("/index")
//    public String index(){
//        return "survey/index";
//    }

//    @RequestMapping(value = "/index",headers="Host=poll.oilchem.net")
    @RequestMapping(value = "/home")
    public String tosurvey(HttpServletRequest request, Model model/*,@CookieValue("voted") String voted*/) {
        try {
            String userid = getCookieValue(request,"userid");
            String username = getCookieValue(request, "username");
            String voted = getCookieValue(request, "voted");

            SurveyRepository.User user = null;
            if (userid!=null && isNumeric(userid)) {
                user = surveyRepository.findUserById(Integer.valueOf(userid));
            } else if (isNotBlank(username)) {
                user = surveyRepository.findUserByUserName(username);
            }
            if (user != null) {
                model.addAttribute("userName", user.getUserName())
                        .addAttribute("realName", user.getRealName())
                        .addAttribute("userAddress", user.getUserAddress());
            }

            if (surveyRepository.getIpVoted(getIpAddr(request)) >= 10 || "true".equals(voted)) {
                model.addAttribute("voted", true);
                return "survey/index";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "survey/index";
        }
        return "survey/index";
    }


    @RequestMapping("/survey/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response, Survey survey, Model model
                        /* ,@CookieValue("voted") String voted,@CookieValue("userid") String userid,
                         @CookieValue("username") String username*/) {
        try {
            String userid = getCookieValue(request,"userid");
            String username = getCookieValue(request, "username");
            String voted = getCookieValue(request, "voted");

            SurveyRepository.User user = null;
            if (userid!=null && isNumeric(userid)) {
                user = surveyRepository.findUserById(Integer.valueOf(userid));
            } else if (isNotBlank(username)) {
                user = surveyRepository.findUserByUserName(username);
            }
            if (user != null) {
                survey.setUserId(user.getId());
                survey.setUserMobie(user.getUserName());
                survey.setUserName(user.getRealName());
                survey.setUserAddress(user.getUserAddress());
                model.addAttribute("userName", user.getUserName())
                        .addAttribute("realName", user.getRealName())
                        .addAttribute("userAddress", user.getUserAddress());
            }

            String ip = getIpAddr(request);
            survey.setIp(ip);

            if (surveyRepository.getIpVoted(ip) >= 10 || "true".equals(voted)) {
                model.addAttribute("voted", true);
                return "survey/index";
            }
            if (!badSurvey(survey)) {
                model.addAttribute("dataEmpty", true);
                return "survey/index";
            }

            surveyRepository.insert(survey);
            model.addAttribute("success",true);
            response.addCookie(new Cookie("voted", "true"));
        } catch (Exception e) {
            e.printStackTrace();
            return "survey/index";
        }
        return "survey/index";
    }

    private String getCookieValue(HttpServletRequest request,String name) {
        Cookie cookie = getCookie(request,name);
        if(cookie!=null){
            return cookie.getValue();
        }
        return null;
    }


    private Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            String key = cookies[i].getName();
            String value = cookies[i].getValue();
            if (name.equals(key)) {
                return cookies[i];
            }
        }
        return null;
    }

    private boolean badSurvey(Survey survey) {
        if (isBlank(survey.getQuestion01()) || isBlank(survey.getQuestion02()) ||
                isBlank(survey.getQuestion03()) || isBlank(survey.getQuestion04()) ||
                isBlank(survey.getQuestion05()) || isBlank(survey.getQuestion06()) ||
                isBlank(survey.getQuestion07()) || isBlank(survey.getQuestion08()) ||
                isBlank(survey.getQuestion09()) || isBlank(survey.getQuestion10())) {
            return false;
        }
        return true;
    }


    public boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
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
