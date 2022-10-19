package hello.core;

import hello.core.discount.DicsountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    // 역할과 구현으로 분리
    // 다른 구현체로 쉽게 변경할 수 있음. 어떤역할을 하는 지 보임.
    // 애플리케이션 전체 구성이 어떻게 되어 있는 지 파악 가능함.

    @Bean
    public MemberService memberService(){
        // 생성자 주입
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), dicsountPolicy());
    }

    @Bean
    public DicsountPolicy dicsountPolicy(){
/*
        Appconfig 변경으로 할인 정책 변경 가능함.
        사용영역의 어떤 코드도 변경 X, 구성영역은 당연히 변경됨.
        AppConfig 애플리케이션의 공연 기획자로 생각하자.
        return  new FixDiscountPolicy();
*/
        return new RateDiscountPolicy();
    }
}
