package com.jobifyProject.jobify.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.jobifyProject.jobify.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(COMPANY_READ,
            COMPANY_WRITE,
            JOB_READ,
            JOB_WRITE,
            USER_READ,
            USER_WRITE)),
    USER(Sets.newHashSet(COMPANY_READ,
            JOB_READ,
            USER_READ)),
    COMPANY(Sets.newHashSet(COMPANY_READ,
            COMPANY_WRITE,
            JOB_WRITE,
            JOB_READ,
            USER_READ,
            USER_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<com.jobifyProject.jobify.security.ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("Role_" + this.name()));
        return permissions;

    }
}
