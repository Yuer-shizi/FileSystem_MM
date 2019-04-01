package team.redrock.downloadtool.Filter;

import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "sessionFilter",urlPatterns = {"/*"})
public class SessionFilter implements Filter {


    private String[] includeUrls = new String[]{"/user/","/user/index.html","/user/register.html","/user/js/bootstrap.min.css",
            "/user/js/bootgrid/jquery.bootgrid.css","/user/js/bootgrid/jquery.bootgrid.fa.js","/user/js/bootgrid/jquery.bootgrid.js",
    "/user/css/style.css","/user/css/login.css","/user/css/regist.css","/user/js/util.js", "user/js/chartutils", "user/js/Chart.js", "/user/js/bootstrap.min.js","/user/js/jquery.min.js","/user/list","/user/rootlist","/user/img/logo.png","/user/img/bg.png","/user/img/logoImage.png","/user/img/user.png","/user/img/user1.png","/user/img/password1.png","/user/img/rememberPassword1.png","/user/img/rememberPassword2.png","/user/img/bj.gif",
    "/user/login","/user/register","/user/vedio","/user/audio","/user/text"};

    private boolean isNeedFilter(String uri) {
        for (String includeUrl : includeUrls) {
            if(includeUrl.equals(uri)) {
                return false;
            }
        }
        return true;
    }


    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletrequest;
        HttpServletResponse response = (HttpServletResponse) servletresponse;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        System.out.println("filter url:"+uri);
        //是否需要过滤
        boolean needFilter = isNeedFilter(uri);
        if(!needFilter){
            chain.doFilter(servletrequest, servletresponse);
        }else{
            if(session != null&&session.getAttribute("user")!=null){
//                System.out.println("过滤器中的session"+session.getAttribute("user"));
                chain.doFilter(servletrequest,servletresponse);
            }else{
                String requestType = request.getHeader("X-Requested-With");
                if("XMLHttpRequest".equals(requestType)){
                    response.getWriter().write(JSON.toJSONString("{'status':'-1'},{'content':'你尚未登录'}"));
                }else{
                    response.sendRedirect("/user/");
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
