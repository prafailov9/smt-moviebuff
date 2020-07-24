package org.ntr.domainmodel.models.role;

public enum RoleName {
    ADMIN("ADMIN"),
    USER("USER");

    private final String roleName;

    RoleName(final String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
