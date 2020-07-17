package work.aki7chyan.subgroup.Entity;

import com.alibaba.fastjson.JSONObject;

/**
 * 登录信息
 */
public class LoginInfo {
    //用户标识
    private static String Uid;
    //昵称
    private static String NickName;
    //手机
    private static String MobTel;
    //用户等级
    private static int RankLevel;
    //是否禁用
    private static boolean IsBanned;

    public LoginInfo(String Uid, String NickName, String MobTel, int RankLevel, boolean IsBanned) {
        this.Uid = Uid;
        this.NickName = NickName;
        this.MobTel = MobTel;
        this.RankLevel = RankLevel;
        this.IsBanned = IsBanned;
    }

    /**
     * 获取用户标识
     * @return  用户标识
     */
    public static String getUid() {
        return Uid;
    }

    /**
     * 设置用户标识
     * @param uid 用户标识
     */
    public static void setUid(String uid) {
        Uid = uid;
    }

    /**
     * 获取昵称
     * @return  昵称
     */
    public static String getNickName() {
        return NickName;
    }

    /**
     * 设置昵称
     * @param nickName 昵称
     */
    public static void setNickName(String nickName) {
        NickName = nickName;
    }

    /**
     * 获取手机
     * @return  手机
     */
    public static String getMobTel() {
        return MobTel;
    }

    /**
     * 设置手机
     * @param mobTel 手机
     */
    public static void setMobTel(String mobTel) {
        MobTel = mobTel;
    }

    /**
     * 获取权限等级
     * @return  权限等级
     */
    public static int getRankLevel() {
        return RankLevel;
    }

    /**
     * 设置权限等级
     * @param rankLevel 权限等级
     */
    public static void setRankLevel(int rankLevel) {
        RankLevel = rankLevel;
    }

    /**
     * 获取禁用状态.
     * @return  禁用状态
     */
    public static boolean isIsBanned() {
        return IsBanned;
    }

    /**
     * 设置权限
     * @param isBanned 是否禁用
     */
    public static void setIsBanned(boolean isBanned) {
        IsBanned = isBanned;
    }

    /**
     * 获取Json
     * @return Json
     */
    public JSONObject toJsonText(){
        JSONObject json = new JSONObject();
        json.put("Uid",this.Uid);
        json.put("NickName",this.NickName);
        json.put("MobTel",this.MobTel);
        json.put("RankLevel",this.RankLevel);
        json.put("IsBanned",this.IsBanned);
        return json;
    }
}
