package com.skillup.infrastructure.jooqrepo;

import com.skillup.domain.user.UserDomain;
import com.skillup.domain.user.UserRepository;
import com.skillup.infrastructure.jooq.tables.User;
import com.skillup.infrastructure.jooq.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JooqUserRepo implements UserRepository, RecordDomainMapping<UserRecord, UserDomain> {
    @Autowired
    DSLContext dslContext;

    public static final User USER_T = new User();


    @Override
    public void createUser(UserDomain userDomain) {
        dslContext.executeInsert(toRecord(userDomain));
    }

    @Override
    public UserDomain getUserByUserName(String userName) {

        Optional<UserDomain> userRecordOptional = dslContext
                .selectFrom(USER_T)
                .where(USER_T.USER_NAME.eq(userName))
                .fetchOptional(this :: toDomain);
        return userRecordOptional.orElse(null);
    }

    @Override
    public UserDomain getUserByUserId(String userId) {
        Optional<UserDomain> userRecordOptional = dslContext
                .selectFrom(USER_T)
                .where(USER_T.USER_ID.eq(userId))
                .fetchOptional(this :: toDomain);
        return userRecordOptional.orElse(null);
    }

    @Override
    public void updateUser(UserDomain userDomain) {
        dslContext.executeUpdate(toRecord(userDomain));
    }
    @Override
    public UserRecord toRecord(UserDomain userDomain){
        UserRecord userRecord = new UserRecord();
        userRecord.setUserId(userDomain.getUserId());
        userRecord.setUserName(userDomain.getUserName());
        userRecord.setPassword(userDomain.getPassword());
        return userRecord;
    }

    @Override
    public UserDomain toDomain(UserRecord userRecord){
        return UserDomain.builder()
                .userId(userRecord.getUserId())
                .userName(userRecord.getUserName())
                .password(userRecord.getPassword())
                .build();
        
    }
}
