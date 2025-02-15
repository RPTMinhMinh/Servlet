package vn.edu.t3h.qlnv1.security;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.qlnv1.dao.impl.RoleDaoImpl;
import vn.edu.t3h.qlnv1.model.RoleModel;
import vn.edu.t3h.qlnv1.model.UserModel;
import vn.edu.t3h.qlnv1.service.RoleService;
import vn.edu.t3h.qlnv1.service.impl.RoleServiceImpl;
import vn.edu.t3h.qlnv1.utils.Constants;

import java.io.IOException;
import java.util.logging.LogRecord;


@WebFilter("/*")
public class AuthenticationFilter implements jakarta.servlet.Filter {
    private RoleService roleService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        jakarta.servlet.Filter.super.init(filterConfig);
        roleService = new RoleServiceImpl(new RoleDaoImpl());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        UserModel currentUser = (UserModel) request.getSession().getAttribute(Constants.SESSION_ID_CURRENT_USER);

        String uri = request.getRequestURI();
        if (uri.startsWith("/cms")){
            if (currentUser != null){
                RoleModel roleCurrentUser = roleService.getRoleById(currentUser.getRoleId());
                if (roleCurrentUser != null && roleCurrentUser.getCode().equalsIgnoreCase(Constants.ROLE.ROLE_ADMIN.name())){
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
