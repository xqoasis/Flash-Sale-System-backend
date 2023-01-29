package com.skillup.infrastructure.cache.redis;

import com.skillup.infrastructure.cache.RedisCacheRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static  org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RedisCacheRepoTest {
    @Autowired
    RedisCacheRepo redisCacheRepo;

    @Test
    void setAndGetThenDel(){
        redisCacheRepo.set("project_nam", "skillup");
        String projectName = (String)redisCacheRepo.get("project_name");
        assertThat(projectName).isEqualTo("skillup");
        redisCacheRepo.delete("project_name");
    }
}
