package vn.edu.t3h.bookstore.security;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.bookstore.dao.impl.UserDaoImpl;
import vn.edu.t3h.bookstore.model.User;
import vn.edu.t3h.bookstore.service.UserService;
import vn.edu.t3h.bookstore.service.impl.UserServiceImpl;
import vn.edu.t3h.bookstore.utils.Constants;


import java.io.IOException;


@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        userService = new UserServiceImpl(new UserDaoImpl());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User currentUser = (User) request.getSession().getAttribute(Constants.SESSION_ID_CURRENT_USER);

        String uri = request.getRequestURI();
        if (uri.startsWith("/cms")){
            if (currentUser != null){
//                RoleModel roleCurrentUser = roleService.getRoleById(currentUser.getRoleId());
                if (currentUser.getRole() != null && currentUser.getRole().equalsIgnoreCase(Constants.ROLE.ADMIN.name()) || currentUser.getRole().equalsIgnoreCase(Constants.ROLE.EMPLOYEE.name())){
                    filterChain.doFilter(request, response);
                }else {
                    response.sendRedirect("/login?message="+ Constants.PERMISSION_DENIED);
                }
            }else {
                response.sendRedirect("/login?message="+Constants.DONT_LOGIN);
            }
        }else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
