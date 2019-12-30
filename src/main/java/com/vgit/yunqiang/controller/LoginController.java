package com.vgit.yunqiang.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    /**
     * 用户登录的入口
     *
     * @param username
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "remember", required = false) String remember,
            Model model) {
        String message = "";
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            // 初始化
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            remember = "on"; // 测试
            // 记住我
            if (StringUtils.isNotBlank(remember)) {
                if (remember.equals("on")) {
                    token.setRememberMe(true);
                } else {
                    token.setRememberMe(false);
                }
            } else {
                token.setRememberMe(false);
            }

            try {
                // 登录校验
                subject.login(token);
                LOGGER.info("用户是否登录：{}", subject.isAuthenticated());
                return "redirect:/index";
            } catch (UnknownAccountException e) {
                message = "用户账户不存在！";
                LOGGER.error("用户账户不存在，错误信息：{}", e.getMessage());
            } catch (IncorrectCredentialsException e) {
                message = "用户名或密码错误！";
                LOGGER.error("用户名或密码错误，错误信息：{}", e.getMessage());
            } catch (LockedAccountException e) {
                message = "该账号已锁定！";
                LOGGER.error("该账号已锁定，错误信息：{}", e.getMessage());
            } catch (DisabledAccountException e) {
                message = "该账号已禁用！";
                LOGGER.error("该账号已禁用，错误信息：{}", e.getMessage());
            } catch (ExcessiveAttemptsException e) {
                message = "该账号登录失败次数过多！";
                LOGGER.error("该账号登录失败次数过多，错误信息：{}", e.getMessage());
            } catch (Exception e) {
                message = "未知错误！";
                LOGGER.error("未知错误，错误信息：{}", e.getMessage());
            }
        } else {
            message = "请输入用户名和密码！";
        }
        // 登录失败
        model.addAttribute("message", message);
        return "common/login";
    }

}
