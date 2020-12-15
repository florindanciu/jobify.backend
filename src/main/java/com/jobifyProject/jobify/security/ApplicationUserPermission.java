package com.jobifyProject.jobify.security;

public enum ApplicationUserPermission {
    JOB_READ("job:read"),
    JOB_WRITE("job:write"),
    COMPANY_READ("company:read"),
    COMPANY_WRITE("company:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
