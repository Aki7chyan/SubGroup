package work.aki7chyan.subgroup.Utils;

import com.alibaba.fastjson.JSONObject;
import work.aki7chyan.subgroup.Entity.ResultMsg;

/**
 * 请求结果封装工具类
 */
public class ResultUtil {

    /**
     * 获取返回结果（结果包含json数据）
     * @param code 响应码
     * @param msg 请求结果
     * @param json 请求响应数据
     * @return  Json结果数据
     */
    public static JSONObject getResultJson(String code, String msg, JSONObject json){
        ResultMsg resultMsg = new ResultMsg(code,msg, json);
        JSONObject newJson =  resultMsg.toJsonText();
        return newJson;
    }

    /**
     * 获取返回结果
     * @param code 响应码
     * @param msg 请求结果
     * @param str 请求响应数据
     * @return  Json结果数据
     */
    public static JSONObject getResultJson(String code, String msg, String str){
        ResultMsg resultMsg = new ResultMsg(code,msg, str);
        JSONObject newJson =  resultMsg.toJsonText();
        return newJson;
    }

    /**
     * 获取返回结果（Json格式）
     * @param resultMsg 结果集
     * @return  Json结果数据
     */
    public static JSONObject getResultJson(ResultMsg resultMsg){
        JSONObject newJson =  resultMsg.toJsonText();
        return newJson;
    }
}
