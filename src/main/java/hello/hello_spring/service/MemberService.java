package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Long join(Member member) {
        // 중복 방지
        validateDuplicateMember(member);

        repository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        repository
                .findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("Duplicate User");
                });
    }

    public List<Member> findMembers() {
        return repository.findAll();
    }

    public Optional<Member> findMember(Long id) {
        return repository.findById(id);
    }
}
