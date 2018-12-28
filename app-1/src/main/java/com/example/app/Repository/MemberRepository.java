package com.example.app.Repository;

import com.example.app.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByName(String name);

    Page<Member> findByIdGreaterThanEqual(int id, Pageable pageable);
}
