package gdg.restfulapipracticespringboot.repository;

import gdg.restfulapipracticespringboot.domain.member.Member;
import gdg.restfulapipracticespringboot.domain.member.MemberDTO;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(MemberDTO memberDTO);

    Optional<Member> findById(long id);

    List<Member> findAll();

    void update(Long id, MemberDTO memberDTO);
}
