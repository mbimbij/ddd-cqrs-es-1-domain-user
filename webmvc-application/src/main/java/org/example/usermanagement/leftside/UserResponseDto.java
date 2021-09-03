package org.example.usermanagement.leftside;

import lombok.Value;
import org.example.usermanagement.domain.User;

@Value
public class UserResponseDto {
    Integer id;
    String userName;
    String emailAddress;

    public static UserResponseDto from(User user) {
        return new UserResponseDto(user.getId().getValue(), user.getUserName().getValue(), user.getEmailAddress().getValue());
    }
}
