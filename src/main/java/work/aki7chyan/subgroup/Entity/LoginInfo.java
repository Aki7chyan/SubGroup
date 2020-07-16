package work.aki7chyan.subgroup.Entity;

import com.alibaba.fastjson.JSONObject;

public class LoginInfo {
    private static String Uid;
    private static String NickName;
    private static String MobTel;
    private static int RankLevel;
    private static boolean IsBanned;

    public LoginInfo(String Uid, String NickName, String MobTel, int RankLevel, boolean IsBanned) {
        this.Uid = Uid;
        this.NickName = NickName;
        this.MobTel = MobTel;
        this.RankLevel = RankLevel;
        this.IsBanned = IsBanned;
    }

    public static String getUid() {
        return Uid;
    }

    public static void setUid(String uid) {
        Uid = uid;
    }

    public static String getNickName() {
        return NickName;
    }

    public static void setNickName(String nickName) {
        NickName = nickName;
    }

    public static String getMobTel() {
        return MobTel;
    }

    public static void setMobTel(String mobTel) {
        MobTel = mobTel;
    }

    public static int getRankLevel() {
        return RankLevel;
    }

    public static void setRankLevel(int rankLevel) {
        RankLevel = rankLevel;
    }

    public static boolean isIsBanned() {
        return IsBanned;
    }

    public static void setIsBanned(boolean isBanned) {
        IsBanned = isBanned;
    }

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
