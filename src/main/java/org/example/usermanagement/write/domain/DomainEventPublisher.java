package org.example.usermanagement.write.domain;

import org.example.usermanagement.write.domain.DomainEvent;

public interface DomainEventPublisher {
    void publish(DomainEvent domainEvent);
}
