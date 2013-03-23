package com.ttpod.common.view.Public;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


import com.ttpod.common.model.UserInfo;

import com.ttpod.common.service.IUserManageService;

import com.ttpod.common.view.BaseController;


/**
 * @className:UserController.java
 * @classDescription:用户管理
 * @author:xiayingjie
 * @createTime:2011-5-3
 */
@Controller
@RequestMapping("/public/getInfor")
public class GetInfor extends BaseController {
	@Autowired
	protected IUserManageService userManageService;

	/**
	 * JSON 传输数据
	 */
	@RequestMapping(value = "/json")
	public String login(String inforType, HttpServletRequest request,HttpServletResponse response) {
		   UserInfo user =userManageService.findById(422);
			if (user != null) 
			{
				request.getSession().setAttribute("userAdmin", user);
				String uri="";
				if("phone".equals(inforType))
				{ 
					uri="/manage/manageInfo/phone/list.do";
					
				}else if("phonebrand".equals(inforType))
				{
					uri="/manage/manageInfo/phonebrand/list.do";
				}
				else if("platform".equals(inforType))
				{
					uri="/manage/manageInfo/platform/list.do";
				}
				try {
					request.getRequestDispatcher(uri).forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
		    return null;
	
		}

	public IUserManageService getUserManageService() {
		return userManageService;
	}

	public void setUserManageService(IUserManageService userManageService) {
		this.userManageService = userManageService;
	}
	
	}
