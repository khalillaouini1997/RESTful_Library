package com.pi.restful_library.Services;

import com.pi.restful_library.model.Members;
import com.pi.restful_library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Members> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Members> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public Members addMember(Members members) {
        return memberRepository.save(members);
    }

    public Members updateMember(Long id, Members membersDetails) {
        Members members = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Members not found"));
        members.setName(membersDetails.getName());
        members.setEmail(membersDetails.getEmail());
        members.setMembershipId(membersDetails.getMembershipId());
        return memberRepository.save(members);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}