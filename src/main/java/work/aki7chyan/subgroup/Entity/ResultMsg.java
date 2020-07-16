package work.aki7chyan.subgroup.Entity;

import com.alibaba.fastjson.JSONObject;
import work.aki7chyan.subgroup.Utils.MsgCode;

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

    public static String getErrorCode() {
        return ErrorCode;
    }

    public static void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public static String getMessage() {
        return Message;
    }

    public static void setMessage(String message) {
        Message = message;
    }

    public static Object getData() {
        return Data;
    }

    public static void setData(Object data) {
        Data = data;
    }

    public JSONObject toJsonText(){
        JSONObject json = new JSONObject();
        json.put("ErrorCode",this.ErrorCode);
        json.put("Message",this.Message);
        json.put("Data",this.Data);
        return json;
    }
}
