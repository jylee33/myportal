package com.example.myspringboot.config;

import com.example.myspringboot.aop.TimeTraceAop;
import com.example.myspringboot.repository.JdbcTemplateMemberRepository;
import com.example.myspringboot.repository.JpaMemberRepository;
import com.example.myspringboot.repository.MemberRepository;
import com.example.myspringboot.repository.MemoryMemberRepository;
import com.example.myspringboot.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;
    private EntityManager em;

//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }

}
