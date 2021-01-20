package jpabook.jpashop.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {

    private final MemberRepository memberRepository;

    @Autowired
    MemberRepositoryTest(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testMember() {
        Member member = new Member();
        member.setUsername("memberA");

        Long savedId = memberRepository.save(member);

        Member findMember = memberRepository.find(savedId);

        Assertions.assertThat(member).isEqualTo(findMember);
    }
}