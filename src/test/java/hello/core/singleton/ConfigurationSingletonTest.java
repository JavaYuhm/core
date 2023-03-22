package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurataion(){

        // 확인해보면 같은 인스턴스가 공유되어 사용된다.
        // Appconfig 를 봤을 때 new MemberReposiotry를 호출하는 것처럼 보이나 동일함.


        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 =  orderService.getMemberRepository();

        System.out.println("memberServie -> memberRepository :" +memberRepository1);
        System.out.println("orderServie -> memberRepository :" +memberRepository1);
        System.out.println("memberRepository :" +memberRepository);

        assertThat(memberRepository1).isSameAs(memberRepository);
        assertThat(memberRepository2).isSameAs(memberRepository);
    }

    @Test
    void configurataionDeep(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig appConfig = ac.getBean(AppConfig.class);

        // 스프링에서 CGLIB라는 바이트 코드 조작 라이브러리를 사용해서 AppConfgig 클래스를 상속 받은 임의의 다른 클래스를 만듦.
        System.out.println("appConfig.getClass() = " + appConfig.getClass());
    }
}
