package org.example.usermanagement.write.domain;

import org.example.usermanagement.utils.ValueObject;

public class FirstName extends ValueObject<String> {
    public FirstName(String value) {
        super(value);
    }
}
