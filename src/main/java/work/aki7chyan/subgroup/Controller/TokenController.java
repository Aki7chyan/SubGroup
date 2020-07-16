package work.aki7chyan.subgroup.Controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import work.aki7chyan.subgroup.Config.JwtConfig;
import work.aki7chyan.subgroup.Utils.ResultTool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class TokenController {
    @Resource
    private JwtConfig jwtConfig ;

    /**
     * 用户登录接口
     * @param userName
     * @param passWord
     * @return
     */
    @RequestMapping("/login")
    public JSONObject login (@RequestParam("userName") String userName,
                             @RequestParam("passWord") String passWord){
        JSONObject json = new JSONObject();

        /** 验证userName，passWord和数据库中是否一致，如不一致，直接return ResultTool.errer(); 【这里省略该步骤】*/

        // 这里模拟通过用户名和密码，从数据库查询userId
        // 这里把userId转为String类型，实际开发中如果subject需要存userId，则可以JwtConfig的createToken方法的参数设置为Long类型
        String userId = 5 + "";
        String token = jwtConfig.createToken(userId) ;
        if (!StringUtils.isEmpty(token)) {
            json.put("token",token) ;
        }
        return ResultTool.success(json) ;
    }

    /**
     * 需要 Token 验证的接口
     */
    @RequestMapping("/info")
    public JSONObject info (){
        return ResultTool.success("info") ;
    }

    /**
     * 根据请求头的token获取userId
     * @param request
     * @return
     */
    @RequestMapping("/getUserInfo")
    public JSONObject getUserInfo(HttpServletRequest request){
        String usernameFromToken = jwtConfig.getUsernameFromToken(request.getHeader("token"));
        return ResultTool.success(usernameFromToken) ;
    }
}
