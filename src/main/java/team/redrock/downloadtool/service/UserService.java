package team.redrock.downloadtool.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import team.redrock.downloadtool.entity.User;
import team.redrock.downloadtool.jpa.UserJPA;
import team.redrock.downloadtool.utils.Response;
import team.redrock.downloadtool.utils.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserJPA userJPA;

    public Response userRegister(String username, String password) {
        if (Utility.IsNull(username)) {
            return new Response("-1", "用户名不能为空!");
        }
        if (Utility.IsNull(password)) {
            return new Response("-1", "密码不能为空！");
        }
        if (userJPA.findByUsername(username) != null) {
            return new Response("-1", "用户名已存在！");
        }
        User user = new User();
        user.setUsername(username);
//        String encodedpassword = Encoding.getMD5(password);
        user.setPassword(password);
        user.setIsPrime(0);
        userJPA.save(user);
        return new Response("0", JSON.toJSONString(user));

    }

    public Response userLogin(String username, String password, HttpServletRequest request) {
        if (Utility.IsNull(username)) {
            return new Response("-1", "用户名不能为空");
        }
        if (Utility.IsNull((password))) {
            return new Response("-1", "密码不能为空");
        }
        User user = userJPA.findByUsername(username);
        if (user == null) {
            return new Response("-1", "用户名不存在");
        } else {
            if (!user.getPassword().equals(password)) {
                return new Response("-1", "密码错误");
            } else {
                request.getSession().setAttribute("user", user.getUsername());
//                String user1 = (String) request.getSession().getAttribute("user");
//                System.out.println("session为" + user1);
                return new Response("0", user);
            }
        }
    }

    public JSONObject userList(Integer current, Integer rowCount) {
        JSONObject jsonObject = new JSONObject();
        List<User> users;
        if (rowCount == -1)
            users = userJPA.findAll();
        else {
            users = userJPA.findAll(PageRequest.of((current - 1) * rowCount, rowCount)).getContent();
        }
        jsonObject.put("current", current);
        jsonObject.put("rowCount", rowCount);
        jsonObject.put("rows", users);
        jsonObject.put("total", userJPA.count());
        return jsonObject;
    }

    public Response getUserById(Long id) {
        Optional<User> op = userJPA.findById(id);
        return op.map(user -> new Response("0", user)).orElseGet(() -> new Response("-1", "用户不存在：id=" + id));
    }

    public Response add(User user) {
        if (userJPA.findByUsername(user.getUsername()) != null)
            return new Response("-1", "用户名已存在");
        user.setPassword("123456");
        user.setIsPrime(0);
        userJPA.save(user);
        return new Response("0", "添加成功");
    }

    public Response update(User user) {
        Optional<User> op = userJPA.findById(user.getId());
        if (op.isPresent()) {
            user.setPassword(op.get().getPassword());
            user.setIsPrime(op.get().getIsPrime());
            userJPA.save(user);
            return new Response("0", "修改成功");
        }
        return new Response("-1", "用户编号不存在，内部异常");
    }

    public Response delete(Long id) {
        userJPA.deleteById(id);
        return new Response("0", "删除成功");
    }

    public Response changePassword(User user, String newPassword) {

        User byUsername = userJPA.findByUsername(user.getUsername());
        if (!byUsername.getPassword().equals(user.getPassword())) {
            return new Response("-1", "密码错误");
        } else {
            byUsername.setPassword(newPassword);
            userJPA.save(byUsername);
        }
        return new Response("0", "密码修改成功");
    }

    @Transactional
    public Response resetPassword(Long id) {
        if (userJPA.resetPassword(id) != 1)
            return new Response("-1", "密码重置失败");
        return new Response("0", "密码重置成功，新密码是123456");
    }
}
