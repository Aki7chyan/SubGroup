package work.aki7chyan.subgroup.Controller;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import org.springframework.util.StringUtils;
import work.aki7chyan.subgroup.Config.JwtConfig;
import work.aki7chyan.subgroup.Entity.LoginInfo;
import work.aki7chyan.subgroup.Entity.ResultMsg;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录控制层
 */
public class LoginController {

    @Resource
    private static JwtConfig jwtConfig;
    private static String header = "token";

    /**
     * 获取token失效时间
     * @param request
     * @return 检查登录状态
     */
    public static ResultMsg checkUserToken(HttpServletRequest request){
        String token = request.getHeader(header);
        return checkUserToken(token);
    }

    /**
     * 检查token
     * @param token
     * @return 检查登录状态
     */
    public static ResultMsg checkUserToken(String token){
        ResultMsg resultMsg = new ResultMsg("200","","");//默认token通过
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
        //token验证通过，将token存放至Data进行返回
        resultMsg.setData(token);
        return resultMsg;
    }

    /**
     * 获取当前token用户
     * @param token
     * @return 用户登录信息
     */
    public static LoginInfo getLoginUser(String token){
        String usernameFromToken = jwtConfig.getUsernameFromToken(token);
        JSONObject json = JSONObject.parseObject(usernameFromToken);
        LoginInfo loginInfo = json.toJavaObject(LoginInfo.class);
        return loginInfo;
    }
}
