package work.aki7chyan.subgroup.Entity;

import com.alibaba.fastjson.JSONObject;
import work.aki7chyan.subgroup.Utils.MsgCode;

/**
 * 返回结果信息
 */
public class ResultMsg {
    private static String ErrorCode;
    private static String Message;
    private static Object Data;

    public ResultMsg() { }

    public ResultMsg(String code, JSONObject data) {
        this.ErrorCode = code;
        this.Message = MsgCode.getMsgByCode(code);
        this.Data = data;
    }

    public ResultMsg(String code, String msg, JSONObject data) {
        this.ErrorCode = code;
        this.Message = msg;
        this.Data = data;
    }

    public ResultMsg(String code, String msg, String data) {
        this.ErrorCode = code;
        this.Message = msg;
        this.Data = data;
    }

    /**
     * 获取响应码
     * @return  响应码
     */
    public static String getErrorCode() {
        return ErrorCode;
    }

    /**
     * 设置响应码
     * @param errorCode 响应码
     */
    public static void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    /**
     * 获取相应信息
     * @return  相应信息
     */
    public static String getMessage() {
        return Message;
    }

    /**
     * 设置相应信息
     * @param message 相应信息
     */
    public static void setMessage(String message) {
        Message = message;
    }

    /**
     * 获取相应数据
     * @return  相应数据
     */
    public static Object getData() {
        return Data;
    }

    /**
     * 设置相应数据
     * @param data 相应数据
     */
    public static void setData(Object data) {
        Data = data;
    }

    /**
     * 获取Json
     * @return  Json
     */
    public JSONObject toJsonText(){
        JSONObject json = new JSONObject();
        json.put("ErrorCode",this.ErrorCode);
        json.put("Message",this.Message);
        json.put("Data",this.Data);
        return json;
    }
}
