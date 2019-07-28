package cn.itcast.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component
public class SmsListener {
    //注入工具类
    @Autowired
    private SmsUtil smsUtil;
    //配置监听的消息队列
    @JmsListener(destination="sms")
    public void onMessage(Map map) {
        String phoneNumbers = map.get("phoneNumbers").toString();
        String templateId = map.get("templateId").toString();
        ArrayList<String> params = new ArrayList<>();
        String[] string = ((String) map.get("params")).split(" ");
        params.add(string[0]);
        params.add(string[1]);
        String smsSign = (String) map.get("smsSign");
        smsUtil.sendSms(phoneNumbers,templateId,params,smsSign);
        smsUtil.receiveSms();
        System.out.println(map);
    }
}
