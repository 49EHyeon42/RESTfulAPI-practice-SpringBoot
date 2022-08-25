package gdg.restfulapipracticespringboot.repository;

import gdg.restfulapipracticespringboot.domain.member.Member;
import gdg.restfulapipracticespringboot.domain.member.MemberDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static final ConcurrentHashMap<Long, Member> store = new ConcurrentHashMap<>();
    private static final AtomicLong sequence = new AtomicLong();

    @Override
    public Member save(MemberDTO memberDTO) {
        Member member = new Member();

        member.createMember(sequence.getAndSet(sequence.get() + 1), memberDTO);

        store.put(member.getId(), member);

        return member;
    }

    @Override
    public Optional<Member> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Long id, MemberDTO memberDTO) {
        Optional<Member> optFindMember = findById(id);

        if (optFindMember.isPresent()) {
            Member findMember = optFindMember.get();
            findMember.updateMember(memberDTO);
        } else {
            // TODO 나중에 구현
        }
    }

    // For testing
    public void clearStore() {
        store.clear();
    }
}
