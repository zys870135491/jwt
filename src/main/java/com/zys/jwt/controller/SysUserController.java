package com.zys.jwt.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.zys.jwt.entity.User;
import com.zys.jwt.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/sysuser")
@Slf4j
public class SysUserController {

    @GetMapping(value = "/login")
    public  Map<String, Object>  login(@RequestParam String userName){
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, String> payload = new HashMap<>();
            payload.put("userName",userName);
            payload.put("id",String.valueOf(1));
            String token = JwtUtils.createToken(payload);

            map.put("token",token);
            map.put("userName",userName);
            map.put("id",1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @GetMapping(value = "/menu")
    public String menu(HttpServletRequest request){
        String token = request.getHeader("token");
        DecodedJWT verify = JwtUtils.verifyToken(token);
        log.info(verify.getClaim("userName").asString()+"----"+verify.getClaim("id").asInt());
        return "menu";
    }

}
