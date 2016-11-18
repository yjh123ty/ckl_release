package tech.youmu.ckl.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class SendSmsUtil {

    private static Logger log=LoggerFactory.getLogger(SendSmsUtil.class);

	public static boolean sendMessage(String mobiles, String smsParam,String smsTemplateCode) {
		TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest",
				ConfigUtil.getSMSAppkey(),
				ConfigUtil.getSMSSecret());
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		// 设置公共回传参数
		// req.setExtend("13547899969");
		// 设置短信类型，传入值请填写normal
		req.setSmsType(ConfigUtil.getSMSType());
		// 设置短信签名
		req.setSmsFreeSignName(ConfigUtil.getSMSSign());
		// 设置短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。
		req.setRecNum(mobiles);
		// 设置短信模板ID，传入的模板必须是在阿里大鱼“管理中心-短信模板管理”中的可用模板。
		req.setSmsTemplateCode(smsTemplateCode);
		// 设置短信模板变量，传参规则{"key":"value"}，key的名字须和申请模板中的变量名一致，多个变量之间以逗号隔开。
		req.setSmsParam(smsParam);
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
			log.info("sendResultMessage:"+rsp.getMsg()+";messageCode:"+rsp.getErrorCode());
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return rsp.isSuccess();
	}
}
