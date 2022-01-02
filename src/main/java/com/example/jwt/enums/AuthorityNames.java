package com.example.jwt.enums;

public enum AuthorityNames implements BaseEnum<String> {

    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String value;

    @Override
    public String getValue() {
        return this.value;
    }

    AuthorityNames(String value) {
        this.value = value;
    }

}
