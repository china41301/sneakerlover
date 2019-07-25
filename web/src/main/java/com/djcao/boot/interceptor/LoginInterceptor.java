package com.djcao.boot.interceptor;

import static com.djcao.boot.common.CodeDef.CURRENT_USER;
import static com.djcao.boot.common.CodeDef.NOT_LOGIN;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.djcao.boot.common.PackageResult;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object
        o) throws Exception {
        if (!request.getRequestURI().contains("login")){
            Object attribute = request.getSession().getAttribute(CURRENT_USER);
            if (attribute == null){
                response.setContentType("application/json;charset=utf-8");
                OutputStreamWriter writer =
                    new OutputStreamWriter(response.getOutputStream(), "utf-8");
                PackageResult error = PackageResult.error("未登录");
                error.setCode(NOT_LOGIN);
                writer.write(JSON.toJSONString(error));
                writer.flush();
                return false;
            }
        }
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}