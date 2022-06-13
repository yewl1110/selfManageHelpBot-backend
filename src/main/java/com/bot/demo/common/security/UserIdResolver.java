package com.bot.demo.common.security;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import sun.misc.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserIdResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if(parameter.getParameterType() == String.class && "userId".equals(parameter.getParameterName())) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        String userId = null;
        // contenttype application/json 만 가능한듯..
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);

        if(MediaType.APPLICATION_JSON_VALUE.equals(servletRequest.getHeader(HttpHeaders.CONTENT_TYPE))) {
            StringBuilder body = new StringBuilder();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(servletRequest.getInputStream()));
                char[] buffer = new char[1024];
                while(reader.read(buffer) != -1) {
                    body.append(String.valueOf(buffer).trim());
                }
                if(!ObjectUtils.isEmpty(body)) {
                    JSONObject jsonObject = new JSONObject(body.toString());
                    userId = String.valueOf(jsonObject.get("userId"));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return userId;
    }
}
