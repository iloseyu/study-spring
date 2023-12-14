package hello.core.singleton;

public class StateFulService {
//    private int price;  //상태를 유지하는 필드

//     public void order(String name, int price) {
//         System.out.println("name = " + name + " / price = " + price);
//         this.price = price;
//
//         // 실제로는 여기서 값 받을 때도 문제가 됨
//     }

     // 스프링은 항상 무상태로 설계해야 함
    public int order(String name, int price) {
        System.out.println("name = " + name + " / price = " + price);
        return price;
    }

    public int getPrice() {
//         return price;
        return 0;
    }

}
