package com.bobo.handler;

import cn.hutool.json.JSONUtil;
import com.bobo.vo.ErrorCode;
import com.bobo.vo.R;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 没有权限时返回的内容
 * @Date 2022/1/13 5:08 PM
 * @Created by bobo
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(new R<>(ErrorCode.UnAuthorization.getCode(),ErrorCode.UnAuthorization.getMsg())));
        response.getWriter().flush();
    }
}