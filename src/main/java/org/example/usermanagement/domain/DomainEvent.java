package org.example.usermanagement.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public abstract class DomainEvent {
    protected UserId userId;
}
