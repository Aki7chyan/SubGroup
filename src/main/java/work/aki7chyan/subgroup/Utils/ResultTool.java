package work.aki7chyan.subgroup.Utils;

import com.alibaba.fastjson.JSONObject;

public class ResultTool {

    private static String code;
    private static String msg;
    private static String data;

    private ResultTool(String msg) {
        this.code = "200";
        this.msg = msg;
    }

    public static String getCode() {
        return code;
    }

    public static void setCode(String code) {
        ResultTool.code = code;
    }

    public static String getMsg() {
        return msg;
    }

    public static void setMsg(String msg) {
        ResultTool.msg = msg;
    }

    public static String getData() {
        return data;
    }

    public static void setData(String data) {
        ResultTool.data = data;
    }

    public static JSONObject success(String msg){
        JSONObject object = new JSONObject();
        object.put("info",msg);
        return object;
    }

    public static JSONObject success(JSONObject msg){
        return msg;
    }
}
