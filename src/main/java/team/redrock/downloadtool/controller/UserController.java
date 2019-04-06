package team.redrock.downloadtool.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.redrock.downloadtool.entity.User;
import team.redrock.downloadtool.service.UserService;
import team.redrock.downloadtool.utils.Response;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/register")
    public Response userRegister(@RequestParam String username, @RequestParam String password){
        return userService.userRegister(username,password);
    }

    @PostMapping(value = "/login")
    public Response userLogin(@RequestParam String username, @RequestParam String password, HttpServletRequest request){
        return userService.userLogin(username,password,request);
    }

    @GetMapping(value = "/exit")
    public Response userLogoff(HttpServletRequest request, HttpServletResponse response){
        HttpSession session =request.getSession(false);
        session.invalidate();

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
            return new Response("0", "退出登录成功");
    }

    @PostMapping(value = "/user-list")
    public JSONObject userList(@RequestParam Integer current, @RequestParam Integer rowCount) {

        return userService.userList(current, rowCount);
    }

    @GetMapping(value = "/get-user-by-id")
    public Response getUserById(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/add-user")
    public Response add(User user) {
        return userService.add(user);
    }

    @PostMapping(value = "/update-user")
    public Response update(User user) {
        return userService.update(user);
    }

    @PostMapping(value = "/delete-user-by-id")
    public Response delete(Long id) {
        return userService.delete(id);
    }

    @PostMapping(value = "/change-password")
    public Response changePassword(User user, String newPassword) {
        return userService.changePassword(user, newPassword);
    }

    @PostMapping(value = "/reset-password-by-id")
    public Response resetPassword(Long id) {
        return userService.resetPassword(id);
    }

}
