package work.aki7chyan.subgroup.Controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import work.aki7chyan.subgroup.Config.JwtConfig;
import work.aki7chyan.subgroup.Entity.LoginInfo;
import work.aki7chyan.subgroup.Entity.ResultMsg;
import work.aki7chyan.subgroup.Utils.ResultTool;
import work.aki7chyan.subgroup.Utils.ResultUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static work.aki7chyan.subgroup.Controller.LoginController.checkUserToken;
import static work.aki7chyan.subgroup.Controller.LoginController.getLoginUser;

/**
 * token相关控制层
 */
@RestController
public class TokenController {
    @Resource
    private JwtConfig jwtConfig ;

    /**
     * 用户登录接口
     * @param userName
     * @param passWord
     * @return token字符串
     */
    @RequestMapping("/login")
    public JSONObject login (@RequestParam("userName") String userName,
                             @RequestParam("passWord") String passWord){
        JSONObject json = new JSONObject();

        /** 验证userName，passWord和数据库中是否一致，如不一致，直接return ResultTool.errer(); 【这里省略该步骤】*/

        // 这里模拟通过用户名和密码，从数据库查询userId
        // 这里把userId转为String类型，实际开发中如果subject需要存userId，则可以JwtConfig的createToken方法的参数设置为Long类型
        LoginInfo loginInfo = new LoginInfo(userName,passWord,"","",0,false);
        String str = loginInfo.toJsonText().toJSONString();
        String token = jwtConfig.createToken(str);
        ResultMsg resultMsg = new ResultMsg("200","Success",token);
        JSONObject srt = resultMsg.toJsonText();
        return srt;
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
     * @return 用户信息
     */
    @RequestMapping("/getUserInfo")
    public JSONObject getUserInfo(HttpServletRequest request){
        String token;
        ResultMsg resultMsg = checkUserToken(request);
        if(resultMsg.getErrorCode() != "200"){
            return ResultUtil.getResultJson(resultMsg);
        }else{
            token = (String) resultMsg.getData();
        }
        LoginInfo loginInfo = getLoginUser(token);
        JSONObject newJson = ResultUtil.getResultJson("200","Success",loginInfo.toJsonText());
        return newJson;
    }
}
