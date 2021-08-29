package org.example.usermanagement.domain.core;

import org.example.usermanagement.utils.ValueObject;

public class FirstName extends ValueObject<String> {
    public FirstName(String value) {
        super(value);
    }
}
