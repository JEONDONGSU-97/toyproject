package My.toyproject.domain;

import My.toyproject.domain.status.DeliveryStatus;
import My.toyproject.domain.status.OrderStatus;
import My.toyproject.exception.CancelOrderException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;
    private int totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    //연관관계 메서드(하나의 메서드로 모두 처리)
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void setOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    /**
     *  비즈니스 로직
     */
    //정적 팩토리 메서드(생성 메서드)
    //주문 생성
    public static Order createOrder(Member member, Delivery delivery, List<OrderItem> orderItems) {
        Order order = new Order();
        order.setMember(member); //연관관계 메서드
        order.setDelivery(delivery); //연관관계 메서드
        for (OrderItem orderItem : orderItems) {
            order.setOrderItem(orderItem); //연관관계 메서드
        }
        order.setOrderStatus(OrderStatus.READY);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //주문 취소
    public void cancelOrder() {
        if (delivery.getStatus() == DeliveryStatus.COMPLETE) {
            throw new CancelOrderException("이미 배송 완료된 상품입니다. ㅠㅠ");
        }

        this.setOrderStatus(OrderStatus.CANCEL);
        this.getDelivery().setStatus(DeliveryStatus.CANCEL);

        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    //주문 가격 조회
    public int totalOrderPrice() {
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.totalPrice();
        }

        return totalPrice;
    }

    //주문 상품 조회
}
