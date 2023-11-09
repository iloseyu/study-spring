package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {


    private static Map<Long, Member> store = new HashMap<>();
    // 실무에선 동시성 문제가 있을 수 있어서,
    // 공유되는 변수일 땐 ConcurrentHashMap을 사용하는데, 오늘은 예제니까 그냥 HashMap 사용
    private static long sequence = 0L;
    // 시스템에서 부여하는 회원 id
    // 요것도 실무에선 long보다는 AtomicLong이 낫지만 또한 예제니까 그냥 long 사용

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        // store에 put하기 전에 member ID를 세팅해주고 이름은 넘어온 상태
        store.put(member.getId(), member);
        // store에 put하여 아이디가 저장됨
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 그냥 store.get(id)를 했을 때 null이면 반환되지 못 하니
        // Optional.ofNullable()로 감쌈
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 람다 형식으로, 이름이 서로 같은지 확인하고, 같은 경우만 필터링
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
