package edu.hawaii.its.api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import edu.hawaii.its.api.configuration.SpringBootWebApplication;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("integrationTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = { SpringBootWebApplication.class })
public class TestMemberService {

    @Test
    public void testIsAdmin(){
    }

    @Test
    public void testIsMember(){
    }

    @Test
    public  void testIsIncludeMember(){
    }

    @Test
    public  void testIsExcludeMember(){
    }

    @Test
    public void testIsOwner(){
    }

}
