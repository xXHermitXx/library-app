package com.example.library.service;

import com.example.library.model.Member;
import com.example.library.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    public Optional<Member> getMemberById(Long id) {
        return repository.findById(id);
    }

    public Member addMember(Member member) {
        return repository.save(member);
    }

    public void deleteMember(Long id) {
        repository.deleteById(id);
    }
}
