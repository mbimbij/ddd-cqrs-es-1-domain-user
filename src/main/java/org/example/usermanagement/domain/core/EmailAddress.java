package org.example.usermanagement.domain.core;

import org.example.usermanagement.utils.ValueObject;

public class EmailAddress extends ValueObject<String> {
    public EmailAddress(String value) {
        super(value);
    }
}
