package org.example.usermanagement.domain;

import org.example.usermanagement.utils.ValueObject;

public class UserId extends ValueObject<Integer> {
    private static int sequence;

    private UserId(Integer value) {
        super(value);
    }

    public static UserId nextUserId() {
        return new UserId(sequence++);
    }
}
