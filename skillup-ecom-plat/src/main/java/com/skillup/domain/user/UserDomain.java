package com.skillup.domain.user;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDomain {
    private String userId;
    private String userName;
    private String password;

}
