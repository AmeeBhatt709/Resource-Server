package com.example.resourceServer.config;

import com.example.resourceServer.repository.PageRepository;
import com.example.resourceServer.repository.PrivilegeRepository;
import com.example.resourceServer.repository.RoleUserMappingRepository;
import com.example.resourceServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.web.method.HandlerMethod;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {
    private final UserRepository userRepository;
    private final RoleUserMappingRepository roleUserMappingRepository;
    private final PageRepository pageRepository;
    private final PrivilegeRepository privilegeRepository;
    private AuthenticationTrustResolver trustResolver =
            new AuthenticationTrustResolverImpl();

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
            Authentication authentication, MethodInvocation invocation) {

        CustomMethodSecurityExpressionRoot root =
                new CustomMethodSecurityExpressionRoot(authentication,invocation,pageRepository,privilegeRepository,
                        userRepository,roleUserMappingRepository);
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(this.trustResolver);
        root.setRoleHierarchy(getRoleHierarchy());



        return root;
    }
}
