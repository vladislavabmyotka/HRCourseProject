package by.bsuir.abmyotkashevtsov.controller.filter;

import by.bsuir.abmyotkashevtsov.constant.PathConstant;
import by.bsuir.abmyotkashevtsov.domain.Account;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.bsuir.abmyotkashevtsov.constant.AccountAttachmentConstant.ADMIN_ATTACHMENT;
import static by.bsuir.abmyotkashevtsov.constant.AccountAttachmentConstant.CANDIDATE_ATTACHMENT;
import static by.bsuir.abmyotkashevtsov.constant.AccountAttachmentConstant.EMPLOYER_ATTACHMENT;

/**
 * A filter designed to transfer an authorized user whose role is stored in the session to the corresponding
 * home page.
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = { "/index.jsp" })
public class AuthorizationFilter implements Filter {

    public AuthorizationFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession();

        Account user = (Account)session.getAttribute("role");

        if (user != null) {
            switch (user.getAttachment()) {
                case ADMIN_ATTACHMENT:
                    dispatch(request, response, PathConstant.PATH_PAGE_ADMIN);
                    break;
                case CANDIDATE_ATTACHMENT:
                    dispatch(request, response, PathConstant.PATH_PAGE_CANDIDATE);
                    break;
                case EMPLOYER_ATTACHMENT:
                    dispatch(request, response, PathConstant.PATH_PAGE_EMPLOYER);
                    break;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void dispatch(ServletRequest request, ServletResponse response, String page)
            throws  ServletException, IOException {
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {

    }
}
