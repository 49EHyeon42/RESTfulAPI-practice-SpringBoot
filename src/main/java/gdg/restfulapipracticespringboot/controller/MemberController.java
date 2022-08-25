package gdg.restfulapipracticespringboot.controller;

import gdg.restfulapipracticespringboot.domain.member.Member;
import gdg.restfulapipracticespringboot.domain.member.MemberDTO;
import gdg.restfulapipracticespringboot.repository.MemberRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @PostMapping("/members")
    public Object saveMember(MemberDTO memberDTO) {
        memberRepository.save(memberDTO);

        return "ok";
    }

    @GetMapping("/members/{id}")
    public Object getMember(@PathVariable Long id) {
        Optional<Member> optFindMember = memberRepository.findById(id);

        if (optFindMember.isPresent()) {
            Member member = optFindMember.get();

            return new MemberDTO(member.getEmail(), member.getName(), member.getAge());
        } else {
            return "FAIL : There are no member";
        }
    }

    @GetMapping("/members")
    public Object getMembers() {
        return memberRepository.findAll();
    }

    // TODO 없는 것에 대한 예외 처리
    @PostMapping("/members/{id}")
    public Object updateMember(@PathVariable Long id, MemberDTO memberDTO) {
        memberRepository.update(id, memberDTO);
        return "ok";
    }
}
