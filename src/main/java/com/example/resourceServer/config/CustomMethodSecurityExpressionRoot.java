package com.example.resourceServer.config;

import com.example.resourceServer.entity.Page;
import com.example.resourceServer.entity.Privilege;
import com.example.resourceServer.entity.RoleUSerMapping;
import com.example.resourceServer.entity.User;
import com.example.resourceServer.repository.*;
import com.google.gson.Gson;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;


public class CustomMethodSecurityExpressionRoot
        extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private final PageRepository pageRepository;
    private final PrivilegeRepository privilegeRepository;
    private final UserRepository userRepository;
    private final RoleUserMappingRepository roleUserMappingRepository;
    private final MethodInvocation invocation;

    public CustomMethodSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation,
                                              PageRepository pageRepository,
                                              PrivilegeRepository privilegeRepository1,
                                              UserRepository userRepository,
                                              RoleUserMappingRepository roleUserMappingRepository
                                           ) {
        super(authentication);
        this.invocation=invocation;
        this.pageRepository=pageRepository;
        this.privilegeRepository=privilegeRepository1;
        this.userRepository=userRepository;
        this.roleUserMappingRepository=roleUserMappingRepository;

    }

    public boolean isMember(String permission ) {

        User user=this.userRepository.findByEmail(this.getAuthentication().getName());
        RoleUSerMapping roleUSerMapping=this.roleUserMappingRepository.findByUser(user).get();
        Page page= pageRepository.findByNameAndRoleUSerMapping(invocation.getMethod().getName(),roleUSerMapping).orElse(null);
        Privilege privilege=privilegeRepository.findByRoleUSerMapping(page.getRoleUSerMapping()).get();

        return switch (permission) {
            case "read" -> privilege.getReadAccess();
            case "write" -> privilege.getRemoveAccess();
            case "remove" -> privilege.getWriteAccess();
            default -> false;
        };



//        if(privilege.getReadAccess().equals(permission))
//        {
//            return  privilege.getReadAccess().equals(true)?true : false;
//        }
//        if(privilege.getRemoveAccess().equals(permission))
//        {
//            return  privilege.getRemoveAccess().equals(true)?true : false;
//        }
//        if(privilege.getWriteAccess().equals(permission))
//        {
//            return  privilege.getWriteAccess().equals(true)?true : false;
//        }

    }



    @Override
    public void setFilterObject(Object filterObject) {

    }

    @Override
    public Object getFilterObject() {
        return null;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        System.out.println("new Gson().toJson(returnObject) = " + new Gson().toJson(returnObject));

    }

    @Override
    public Object getReturnObject() {
        return null;
    }

    @Override
    public Object getThis() {
        return null;
    }
}