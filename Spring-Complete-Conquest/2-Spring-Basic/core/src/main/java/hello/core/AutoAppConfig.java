package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// classes = Configuration.class 설정 이유 : 기본의 AppConfig가 스캔되지 않도록 하기 위해
// 보통 설정 정보를 제외하지는 않지만, 기존의 예제 코드를 남겨놓기 위해!
@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
