package hello.core.discount;

import hello.core.member.Member;

public interface DicsountPolicy {

    /**
     * @return 할인 예상금액
     */
    int discount(Member member, int price);

}
