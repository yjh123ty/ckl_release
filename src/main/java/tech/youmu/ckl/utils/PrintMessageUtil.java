package tech.youmu.ckl.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.Printer;

/**
 * 
 * <p>Title:PrintMessageUtil</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年11月3日下午4:22:24</p>
 * <p>Description:打印机工具类</p>
 */
public class PrintMessageUtil {
    
//    private static String partner      = "6234";                                        //用户id
//    private static String machine_code = "4004515184";                                  //打印机终端号 ...
//    private static String apiKey       = "3cfc88dc9c6d4b216b8742ab29d131641cf963cc";    //API密钥
//    private static String mKey         = "vi8wfq2cyxmu";                                //打印机密钥 ...
    private static Logger log          = LoggerFactory.getLogger(PrintMessageUtil.class);

    public static void main(String[] args) {
        //pmRequest();//查询打印机状态
        Printer printer = new Printer();
        //设置printer的信息...
        printer.setMachineCode("4004515184");
        printer.setApiKey("3cfc88dc9c6d4b216b8742ab29d131641cf963cc");
        printer.setPartner("6234");
        printer.setPrinterKey("vi8wfq2cyxmu");

        StringBuffer sb = new StringBuffer("");
        sb.append("点菜清单\r");
        sb.append("----------------------\r");
        sb.append("联系人：测试打印\r");
        sb.append("电话：13408086368\r");
        sb.append("用餐时间：2015-04-09 13:01-13:30\r");

        System.out.println(sb.toString());

        try {
            sendContent(printer,sb.toString());//打印消息
            //sendRequest(sb.toString());//打印消息
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //打印机是否在线接口0是离线1是在线2是缺纸
    public static boolean pmRequest(Printer printer) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("machine_code", printer.getMachineCode());
        params.put("partner", printer.getPartner());
        String sign = signRequest(params,printer);
        params.put("sign", sign);
        HttpClient httpClient = new HttpClient();
        PostMethod post = new PostMethod("http://open.10ss.net:8888/getstatus.php");

        for (Map.Entry<String, String> entry : params.entrySet()) {
            post.addParameter(entry.getKey(), entry.getValue());
        }

        HttpMethodParams param = post.getParams();
        param.setContentCharset("UTF-8");

        try {
            httpClient.executeMethod(post);
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //如果返回200，表明成功
        if (post.getStatusCode() == 200) {
            try {
                String result;
                result = post.getResponseBodyAsString();
                System.out.println(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            System.out.println("打印失败！");
            return false;
        }

    }

    //打印机打印消息
    public static boolean sendRequest(String content,Printer printer) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("partner", printer.getPartner());
        params.put("machine_code", printer.getMachineCode());
        params.put("content", content);
        String sign = signRequest(params,printer);
        params.put("sign", sign);

        HttpClient httpClient = new HttpClient();
        PostMethod post = new PostMethod("http://open.10ss.net:8888");

        for (Map.Entry<String, String> entry : params.entrySet()) {
            post.addParameter(entry.getKey(), entry.getValue());
        }

        HttpMethodParams param = post.getParams();
        param.setContentCharset("UTF-8");

        try {
            httpClient.executeMethod(post);
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //如果返回200，表明成功
        if (post.getStatusCode() == 200) {
            try {
                String result;
                result = post.getResponseBodyAsString();
                if (result.equals("1")) {//数据已经发送到客户端
                    System.out.println("打印成功");
                    return true;
                } else {
                    System.out.println("打印失败,返回值：" + result);
                    return false;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            System.out.println("打印失败！");
            return false;
        }

    }

    public static boolean sendContent(Printer printer, String content) {
        log.info("系统收到打印指令-------开始打印-------内容为" + content);
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("partner", printer.getPartner());
            params.put("machine_code", printer.getMachineCode());
            String time = String.valueOf(System.currentTimeMillis());
            params.put("time", time);
            String sign = signRequest(params,printer);

            byte[] data = ("partner=" + printer.getPartner() + "&machine_code=" + printer.getMachineCode() + "&content="
                           + content + "&sign=" + sign + "&time=" + time).getBytes();
            URL url = new URL("http://open.10ss.net:8888");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5 * 1000);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "text/html; charset=utf-8");
            conn.setRequestProperty("Content-Length", String.valueOf(data.length));

            //获取输出流
           OutputStream outStream = conn.getOutputStream();
            //传入参数
            outStream.write(data);
            outStream.flush();
            outStream.close();

            //获取输入流
            InputStream is = conn.getInputStream();

            System.out.println(conn.getResponseCode());
            if (conn.getResponseCode() == 200) {
                log.info("打印结束");
                int i = -1;
                byte[] b = new byte[1024];
                StringBuffer result = new StringBuffer();
                while ((i = is.read(b)) != -1) {
                    result.append(new String(b, 0, i));
                }

                String sub = result.toString();
                if (sub.equals("1")) {//数据已经发送到客户端
                    System.out.println("打印成功");
                    return true;
                } else {
                    System.out.println("打印失败,返回值：" + result);
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 打印签名
     * @param params
     * @return
     */
    public static String signRequest(Map<String, String> params,Printer printer) {
        Map<String, String> sortedParams = new TreeMap<String, String>();
        sortedParams.putAll(params);
        Set<Map.Entry<String, String>> paramSet = sortedParams.entrySet();
        StringBuilder query = new StringBuilder();
        query.append(printer.getApiKey());
        for (Map.Entry<String, String> param : paramSet) {
            query.append(param.getKey());
            query.append(param.getValue());
        }
        query.append(printer.getPrinterKey());
        String encryptStr = MD5Util.MD5(query.toString());
        return encryptStr;
    }
}
