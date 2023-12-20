package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("name!");

        String name = helloLombok.getName();
        System.out.println("name = " + name);

        System.out.println("-----------------");

        System.out.println("helloLombok" + helloLombok);
    }

    // Lombok이 Getter, Setter를 자동으로 만들어줌.
    // 생성자 관련된 것도 지원함 (NoArgument~ 이런거)

}
