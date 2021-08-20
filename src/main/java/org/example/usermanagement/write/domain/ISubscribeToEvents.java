package org.example.usermanagement.write.domain;

public interface ISubscribeToEvents {
    void handle(DomainEvent domainEvent);
}
