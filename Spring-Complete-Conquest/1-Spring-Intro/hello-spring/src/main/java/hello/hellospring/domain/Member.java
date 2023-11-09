package hello.hellospring.domain;

import jakarta.persistence.*;

// @Entity는 이제 jpa가 관리함
@Entity
public class Member {
    // strategy = GenerationType.IDENTITY 를 설정하면 ID값 자동으로 넣어짐
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
