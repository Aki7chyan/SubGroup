package work.aki7chyan.subgroup.Controller;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.springframework.util.StringUtils;
import work.aki7chyan.subgroup.Config.JwtConfig;
import work.aki7chyan.subgroup.Entity.LoginInfo;
import work.aki7chyan.subgroup.Entity.ResultMsg;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class LoginController {

    @Resource
    private static JwtConfig jwtConfig;
    private static String header = "token";

    public static ResultMsg checkUserToken(HttpServletRequest request){
        String token = request.getHeader("token");
        return checkUserToken(token);
    }

    public static ResultMsg checkUserToken(String token){
        ResultMsg resultMsg = new ResultMsg("200","","");
        if(StringUtils.isEmpty(token)){
            resultMsg.setErrorCode("416");
            resultMsg.setMessage(header + "不能为空");
            return resultMsg;
        }
        Claims claims;
        try{
            claims = jwtConfig.getTokenClaim(token);
            if(claims == null || jwtConfig.isTokenExpired(claims.getExpiration())){
                resultMsg.setErrorCode("408");
                resultMsg.setMessage(header + "失效，请重新登录。");
                return resultMsg;
            }
        }catch (Exception e){
            resultMsg.setErrorCode("408");
            resultMsg.setMessage(header + "失效，请重新登录。");
            return resultMsg;
        }
        resultMsg.setData(token);
        return resultMsg;
    }

    public static LoginInfo getLoginUser(String token){
        String usernameFromToken = jwtConfig.getUsernameFromToken(token);
        JSONObject json = JSONObject.parseObject(usernameFromToken);
        LoginInfo loginInfo = json.toJavaObject(LoginInfo.class);
        return loginInfo;
    }
}
