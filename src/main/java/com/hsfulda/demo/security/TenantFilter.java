package com.hsfulda.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class TenantFilter extends GenericFilterBean {

    @Value("${allowed.tenants}")
    private String allowedTenantsConfig;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Apply filter to /saas endpoints
        if (req.getRequestURI().startsWith("/saas")) {
            String tenantId = req.getHeader("X-TENANT-ID");

            if (tenantId == null || tenantId.isEmpty()) {
                res.sendError(HttpServletResponse.SC_FORBIDDEN, "Missing Tenant ID");
                return;
            }

            List<String> allowedTenants = Arrays.asList(allowedTenantsConfig.split(","));
            if (!allowedTenants.contains(tenantId)) {
                res.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid Tenant ID");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
