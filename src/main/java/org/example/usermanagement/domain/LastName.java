package org.example.usermanagement.domain;

import org.example.utils.ValueObject;

public class LastName extends ValueObject<String> {
    public LastName(String value) {
        super(value);
    }
}
