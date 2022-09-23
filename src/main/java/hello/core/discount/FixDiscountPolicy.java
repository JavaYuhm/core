package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DicsountPolicy{
    private int discountFixAmount = 1000; // 고정


    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return  discountFixAmount;
        } else {
            return 0;
        }
    }
}
