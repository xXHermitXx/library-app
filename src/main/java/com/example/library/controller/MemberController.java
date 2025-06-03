package com.example.library.controller;

import com.example.library.model.Member;
import com.example.library.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping
    public List<Member> getAll() {
        return service.getAllMembers();
    }

    @GetMapping("/{id}")
    public Member getById(@PathVariable Long id) {
        return service.getMemberById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    @PostMapping
    public Member add(@RequestBody Member member) {
        return service.addMember(member);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteMember(id);
    }
}
