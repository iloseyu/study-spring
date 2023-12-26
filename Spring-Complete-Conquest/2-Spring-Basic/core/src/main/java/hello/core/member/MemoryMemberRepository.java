package hello.core.member;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
    public class MemoryMemberRepository implements MemberRepository {

        private static Map<Long, Member> store = new HashMap<>();
        // Hashmap보다 ConcurrentHashMap을 써야 하는데 -> 동시성때문에
        // 예제니까~ 그냥 HashMap 씀.
        @Override
        public void save(Member member) {
            store.put(member.getId(), member);
        }

        @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
