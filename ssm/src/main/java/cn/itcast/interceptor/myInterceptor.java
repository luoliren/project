package cn.itcast.interceptor;

import cn.itcast.domain.Constants;
import cn.itcast.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class myInterceptor implements HandlerInterceptor {
    /**
     * 预处理：controller方法执行前执行
     * return ture放行，执行下一个拦截器，如果没有执行controller中的方法
     * return false不放行
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(String.valueOf(Constants.USER_COUNT));

        if(null == user){
            response.sendRedirect(request.getContextPath()+"/401.jsp");
            return false;
        }
        return true;
    }




    /**
     * 后处理：controller方法执行后执行 success.jsp执行之前
     * @param request
     * @param response
     * @param handler
     * @param
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * success.jsp页面执行后，该方法会执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     * 跳不不了其他的页面
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
