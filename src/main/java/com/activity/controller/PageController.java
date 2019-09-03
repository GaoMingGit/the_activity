package com.activity.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 高铭
 * @date 2019/9/2 - 23:05
 */
@Controller
public class PageController {

    /**
     * 跳转聊天室
     * @param username
     * @param roomId
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @ApiOperation(value = "传用户的账号和活动的aid作为房间号这两个参数，跳转聊天室")
    @RequestMapping(value = "/toChat",method = RequestMethod.POST)
    public void toChat(String username, String roomId, HttpServletResponse response) throws ServletException, IOException {
        //http://gaoming.picp.vip/toChat?username=aaa&roomId=2
        response.sendRedirect("http://gming.qicp.vip/index?username="+username+"&roomId="+roomId);
    }
}
