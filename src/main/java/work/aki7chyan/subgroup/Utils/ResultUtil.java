package work.aki7chyan.subgroup.Utils;

import com.alibaba.fastjson.JSONObject;
import work.aki7chyan.subgroup.Entity.ResultMsg;

public class ResultUtil {

    public static JSONObject getResultJson(String code, String msg, JSONObject json){
        ResultMsg resultMsg = new ResultMsg(code,msg, json);
        JSONObject newJson =  resultMsg.toJsonText();
        return newJson;
    }

    public static JSONObject getResultJson(String code, String msg, String str){
        ResultMsg resultMsg = new ResultMsg(code,msg, str);
        JSONObject newJson =  resultMsg.toJsonText();
        return newJson;
    }

    public static JSONObject getResultJson(ResultMsg resultMsg){
        JSONObject newJson =  resultMsg.toJsonText();
        return newJson;
    }
}
