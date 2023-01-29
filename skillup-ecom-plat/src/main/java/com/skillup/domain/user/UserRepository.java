package com.skillup.domain.user;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    void createUser(UserDomain userDomain);
    UserDomain getUserByUserName(String userName);
    UserDomain getUserByUserId(String userId);
    void updateUser(UserDomain userDomain);
}
