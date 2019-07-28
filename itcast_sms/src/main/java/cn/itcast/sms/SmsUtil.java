package cn.itcast.sms;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
@Component
public class SmsUtil {
    @Autowired
    private Environment env;
    //发送短信
    public void sendSms(String phoneNumbers, String templateId, ArrayList<String> params, String smsSign) {
        try {
            SmsSingleSender ssender = new SmsSingleSender(Integer.parseInt(env.getProperty("appid")), env.getProperty("appkey"));
            SmsSingleSenderResult result = ssender.sendWithParam("86",
                    phoneNumbers,
                    Integer.parseInt(templateId),
                    params,
                    smsSign, "", "");
            System.out.print(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }

    public void receiveSms() {
    }
}
