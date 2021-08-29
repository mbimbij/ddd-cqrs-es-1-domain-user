package org.example.usermanagement.domain.core;

import org.example.usermanagement.utils.ValueObject;

public class LastName extends ValueObject<String> {
    public LastName(String value) {
        super(value);
    }
}
