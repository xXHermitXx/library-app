package com.example.library.service;

import com.example.library.model.Member;
import com.example.library.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MemberServiceTest {

    private MemberRepository memberRepository;
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        memberRepository = mock(MemberRepository.class);
        memberService = new MemberService(memberRepository);
    }

    @Test
    void testAddMember() {
        Member member = new Member("John Doe", LocalDate.of(2023, 1, 1));
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        Member result = memberService.addMember(member);

        assertEquals("John Doe", result.getName());
        assertEquals(LocalDate.of(2023, 1, 1), result.getMembershipDate());
        verify(memberRepository).save(member);
    }

    @Test
    void testGetAllMembers() {
        List<Member> members = List.of(
                new Member("Alice", LocalDate.now()),
                new Member("Bob", LocalDate.now())
        );
        when(memberRepository.findAll()).thenReturn(members);

        List<Member> result = memberService.getAllMembers();

        assertEquals(2, result.size());
        verify(memberRepository).findAll();
    }
}
