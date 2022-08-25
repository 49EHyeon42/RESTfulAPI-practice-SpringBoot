package gdg.restfulapipracticespringboot.repository;

import gdg.restfulapipracticespringboot.domain.member.Member;
import gdg.restfulapipracticespringboot.domain.member.MemberDTO;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    @AfterEach
    void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    void saveAndFindById() {
        // given
        MemberDTO memberDTO = new MemberDTO("test1@gmail.com", "name1", 10);

        // when
        Member savedMember = memoryMemberRepository.save(memberDTO);
        Long savedMemberId = savedMember.getId();

        // then
        Optional<Member> optFindMember = memoryMemberRepository.findById(savedMemberId);

        if (optFindMember.isPresent()) {
            Assertions.assertThat(optFindMember.get()).isEqualTo(savedMember);
        } else {
            Assertions.fail("Empty member");
        }
    }

    @Test
    void findAll() {
        // given
        MemberDTO memberDTO1 = new MemberDTO("test1@gmail.com", "name1", 10);
        MemberDTO memberDTO2 = new MemberDTO("test2@gmail.com", "name2", 20);
        MemberDTO memberDTO3 = new MemberDTO("test3@gmail.com", "name3", 30);

        Member member1 = memoryMemberRepository.save(memberDTO1);
        Member member2 = memoryMemberRepository.save(memberDTO2);
        Member member3 = memoryMemberRepository.save(memberDTO3);

        // when
        List<Member> list = memoryMemberRepository.findAll();

        // then
        Assertions.assertThat(list.size()).isEqualTo(3);
        Assertions.assertThat(list).contains(member1, member2, member3);
    }

    @Test
    void update() {
        // given
        MemberDTO memberDTO = new MemberDTO("test1@gmail.com", "name1", 10);
        Member savedMember = memoryMemberRepository.save(memberDTO);
        Long memberId = savedMember.getId();

        // when
        MemberDTO updateMemberDTO = new MemberDTO("test2@gmail.com", "name2", 20);
        memoryMemberRepository.update(memberId, updateMemberDTO);

        // then
        Optional<Member> optFindMember = memoryMemberRepository.findById(memberId);

        if (optFindMember.isPresent()) {
            Member findMember = optFindMember.get();

            Assertions.assertThat(findMember.getEmail()).isEqualTo(updateMemberDTO.getEmail());
            Assertions.assertThat(findMember.getName()).isEqualTo(updateMemberDTO.getName());
            Assertions.assertThat(findMember.getAge()).isEqualTo(updateMemberDTO.getAge());
        } else {
            Assertions.fail("Empty member");
        }
    }
}
