package work.aki7chyan.subgroup.Utils;

/**
 * 请求响应码枚举类
 */
public enum MsgCode {

    SUCCESS("200", "请求成功"),
    API_NOT_PER("203", "没有该接口的访问权限"),
    NO_DATA("204","查询成功无记录"),
    ERROR("400", "请求失败"),
    API_DISABLE("403", "查询权限已被限制"),
    API_NOT_EXISTS("404", "请求的接口不存在"),
    TIME_OUT("408", "请求超时"),
    ACCOUNT_ERROR("412", "账户不存在或被禁用"),
    PASSWORD_ERROR("413", "账号或密码错误"),
    PARAMS_ERROR("416", "参数为空或格式错误"),
    DATA_IS_HAVE("444", "重复"),
    LOCAL_SYSTEM_ERROR("500", "本地系统异常"),
    ALI_SYSTEM_ERROR("501", "阿里云系统异常"),
    UNKNOWN_IP("502", "非法IP请求"),
    NUMBER_HAVE("600", "手机号已存在"),
    NUMBER_NOT_HAVE("604", "手机号不存在"),
    USER_NAME_HAVE("700", "用户名已存在"),
    USER_NAME_NOT_HAVE("704", "用户名不存在");

    private String code;
    private String msg;

    private MsgCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static String getMsgByCode(String code) {
        MsgCode[] msgArr = MsgCode.values();
        for (MsgCode msg_info : msgArr) {
            if (msg_info.getCode().equals(code)) {
                return msg_info.getMsg();
            }
        }
        return "";
    }
}
