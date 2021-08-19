package org.example.usermanagement;

import org.example.utils.ValueObject;

public class EmailAddress extends ValueObject<String> {
    public EmailAddress(String value) {
        super(value);
    }
}
