package com.example.app.service;

import com.example.app.Repository.MemberRepository;
import com.example.app.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public Page<Member> findByIdGreaterThanEqual(int id, Pageable pageable) {
        return memberRepository.findByIdGreaterThanEqual(id, pageable);
    }
}
