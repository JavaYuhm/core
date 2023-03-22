package hello.core.order;

import hello.core.discount.DicsountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements  OrderService{

    private final MemberRepository memberRepository;
    private final DicsountPolicy dicsountPolicy;
    //private final DicsountPolicy dicsountPolicy = new FixDiscountPolicy();
    //private final DicsountPolicy dicsountPolicy = new RateDiscountPolicy();

    public OrderServiceImpl(MemberRepository memberRepository, DicsountPolicy dicsountPolicy){
        this.memberRepository = memberRepository;
        this.dicsountPolicy = dicsountPolicy;
    }

    @Override
    public Order crateOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = dicsountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName,itemPrice, discountPrice);
    }
    // 테스트용
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
