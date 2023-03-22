package hello.core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

public class StatefulServiceTest {

    @Test
    void StatefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(Testconfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
        
        // ThradeA : a 사용자가 1만원 주문
        statefulService1.order("userA", 10000);
        
        // ThreadB : b 사용자가 2만원 주문
        statefulService2.order("userB", 20000);
        
        // a가 주문금액조회
        int price = statefulService1.getPrice();

        System.out.println("price = " + price);

        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class Testconfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}
