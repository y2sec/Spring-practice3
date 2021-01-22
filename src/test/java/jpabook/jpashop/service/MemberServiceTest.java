package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    @Test
    void 회원가입() {
        Member member = new Member();
        member.setName("kim");

        Long saveId = memberService.join(member);

        Assertions.assertThat(member).isEqualTo(memberService.findOne(saveId));
    }

    @Test
    void 중복회원() throws Exception {
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member1);

        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            return;
        }

        fail("예외가 발생해야 합니다.");

    }

}