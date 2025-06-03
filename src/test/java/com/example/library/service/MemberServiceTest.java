package com.example.library.service;

import com.example.library.model.Member;
import com.example.library.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

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
        Member member = new Member("Test User", "test@example.com");
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        Member result = memberService.addMember(member);

        assertEquals("Test User", result.getName());
        assertEquals("test@example.com", result.getEmail());
        verify(memberRepository).save(member);
    }

    @Test
    void testGetAllMembers() {
        List<Member> members = List.of(
                new Member("Alice", "alice@example.com"),
                new Member("Bob", "bob@example.com")
        );
        when(memberRepository.findAll()).thenReturn(members);

        List<Member> result = memberService.getAllMembers();

        assertEquals(2, result.size());
        verify(memberRepository).findAll();
    }

    @Test
    void testGetMemberById() {
        Member member = new Member("Charlie", "charlie@example.com");
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        Optional<Member> result = memberService.getMemberById(1L);

        assertTrue(result.isPresent());
        assertEquals("Charlie", result.get().getName());
        verify(memberRepository).findById(1L);
    }

    @Test
    void testDeleteMember() {
        doNothing().when(memberRepository).deleteById(1L);

        memberService.deleteMember(1L);

        verify(memberRepository).deleteById(1L);
    }
}
