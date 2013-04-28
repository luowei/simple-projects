package com.rootls.view.others;

import com.rootls.service.CallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static com.rootls.bean.Config.ENABLE_AUTO;
import static org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-4-17
 * Time: 上午9:33
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "getCallLog",value = "/getCallLog",loadOnStartup = 2)
public class TaskServlet extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(TaskServlet.class);

    CallService callService;
    public TaskServlet() {
         callService = getCurrentWebApplicationContext().getBean(CallService.class);
    }

    @Override
    public void init() throws ServletException {
        logger.info("TaskServlet contextInitialized invoke........");

        if(ENABLE_AUTO){
            //10秒钟后开始执行任务
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    callService.runTask();
                }
            },10000);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TaskServlet doGet invoke ==========");

        callService.getCallLog(null, "auto");

        req.getRequestDispatcher("/call/index.do").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    @Override
    public void destroy() {
        callService.stopTask();
    }
}
