package com.pi.restful_library.Controllers;


import com.pi.restful_library.Services.MemberService;
import com.pi.restful_library.model.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Members> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Members> getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Members addMember(@RequestBody Members members) {
        return memberService.addMember(members);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Members> updateMember(@PathVariable Long id, @RequestBody Members membersDetails) {
        return ResponseEntity.ok(memberService.updateMember(id, membersDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}