package hellojpa;

public enum RoleType {
    // USER가 0, ADMIN가 1 따라서 @Enumerated(EnumType.STRING) 필수
    USER, ADMIN
}
