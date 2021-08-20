package org.example.usermanagement.domain;

import org.example.utils.ValueObject;

public class FirstName extends ValueObject<String> {
    public FirstName(String value) {
        super(value);
    }
}
