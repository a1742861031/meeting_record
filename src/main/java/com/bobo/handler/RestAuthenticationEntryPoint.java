package com.bobo.handler;

import cn.hutool.json.JSONUtil;
import com.bobo.vo.ErrorCode;
import com.bobo.vo.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 当用户未登陆或者token失效时返回的结果
 * @Date 2022/1/13 5:11 PM
 * @Created by bobo
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(JSONUtil.parse(new R<>(ErrorCode.Operation_error.getCode(),"当前用户未登陆或凭证失效")));
        response.getWriter().flush();
    }
}
