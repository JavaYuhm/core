package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DicsountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면 중복 오류 발생")
    void findBeanByParentTypeDuplicate(){
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DicsountPolicy.class));
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType(){
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType(){
        Map<String, DicsountPolicy> beanOfType = ac.getBeansOfType(DicsountPolicy.class);
        assertThat(beanOfType.size()).isEqualTo(2);
        for(String key : beanOfType.keySet()){
            System.out.println("key = "+ key + " value" + beanOfType.get(key));
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - object")
    void findAllBean(){
        Map<String, Object> beanOfType = ac.getBeansOfType(Object.class);
       // assertThat(beanOfType.size()).isEqualTo(2);
        for(String key : beanOfType.keySet()){
            System.out.println("key = "+ key + " value" + beanOfType.get(key));
        }
    }

    @Configuration
    static class TestConfig {
        @Bean
        public DicsountPolicy rateDiscountPolicy(){
            return  new RateDiscountPolicy();
        }

        @Bean
        public DicsountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }


    }
}
