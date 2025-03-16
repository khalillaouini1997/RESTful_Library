package com.pi.restful_library.repository;

import com.pi.restful_library.model.Members;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Members, Long> {
    Optional<Members> findByEmail(String email); // New method to find member by email
}