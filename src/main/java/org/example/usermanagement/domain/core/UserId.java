package org.example.usermanagement.domain.core;

import org.example.usermanagement.utils.ValueObject;

public class UserId extends ValueObject<Integer> {
    public UserId(Integer value) {
        super(value);
    }
}
