package com.rootls.bean;

import com.rootls.model.CallResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-4-17
 * Time: 下午3:25
 * To change this template use File | Settings | File Templates.
 */
public class Test {

    public static void main(String[] args){
        ObjectMapper mapper = new ObjectMapper();
//        mapper.getDeserializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        mapper.getSerializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        CallResponse callResponse = null;
        try {
            callResponse = mapper.readValue(new File("d:/longzhong/projects/call-center/WebRoot/data/call_one.json"), CallResponse.class);
//            callResponse = mapper.readValue(new URL("http://localhost:8070/call-center/data/call_one.json"),CallResponse.class);
//            callResponse = mapper.readValue(new URL("http://122.5.105.50:8000/Application/CallLogJson.ashx"), CallResponse.class);
//            mapper.readValue(new File("d:/longzhong/projects/call-center/WebRoot/data/call_one.json"),new TypeReference() {
//                @Override
//                public int compareTo(Object o) {
//                    return 0;  //To change body of implemented methods use File | Settings | File Templates.
//                }
//            });
//            callResponse = mapper.readValue(new URL(CALL_LIST_URL), CallResponse.class);
            System.out.println(callResponse.getErrorCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
