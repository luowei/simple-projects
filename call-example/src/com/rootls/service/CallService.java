package com.rootls.service;

import com.rootls.model.CallRequest;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-4-16
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */
public interface CallService {

    public void runTask();

    public void stopTask();

    public Long getCallLog(CallRequest callRequest,String type);
}
