package com.rootls.view.controller;

import com.rootls.model.CallRequest;
import com.rootls.service.CallService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-4-16
 * Time: 下午1:35
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/call")
public class CallController {

    @Resource
    CallService callService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @ResponseBody
    @RequestMapping("/getLog")
    public String getLog(CallRequest callRequest){
        final CallRequest callRequest2 =  callRequest;
        new Runnable(){
            @Override
            public void run() {
                System.out.println("提交读取任务,读取任务正在后台运行");
                callService.getCallLog(callRequest2,"manul");
            }
        }.run();

        return "已提交读取任务,读取任务正在后台运行";

    }

    @ResponseBody
    @RequestMapping("/getLog2")
    public String getLog2(CallRequest callRequest){
        return "hello world";
    }

}
