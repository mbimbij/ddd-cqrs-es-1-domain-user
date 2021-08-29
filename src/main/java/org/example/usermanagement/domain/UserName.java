package org.example.usermanagement.domain;

import org.example.usermanagement.utils.ValueObject;

public class UserName extends ValueObject<String> {
    public UserName(String value) {
        super(value);
    }
}
