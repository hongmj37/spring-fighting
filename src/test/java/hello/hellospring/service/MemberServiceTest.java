package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.fail;

//command + shift + T 하면 자동으로 Test 생성 가능
class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository; //clear를 위한 !

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
         memberRepository .clearStore();
    }
    @Test
    void join() {
        //given : -무엇인가 주어졌을 때 ( 이데이터 기반으로)
        Member member = new Member();
        member.setName("Hello");
        //when : -한 상황에서 (이걸 검증)
        Long saveId = memberService.join(member);
        //then : 이게 나와야돼
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo( findMember.getName());
    }
    @Test
    public void 중복회원예외() {
        //given
        Member member1 = new Member();
        member1.setName("Spring1");

        Member member2 = new Member();
        member2.setName("Spring1");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        /*
        try{
            memberService.join(member2);
            fail("예외 발생");
        } catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }

         */

        //then
    }
    @Test
    void findMembers() {
    }

    @Test
    void findONe() {
    }
}