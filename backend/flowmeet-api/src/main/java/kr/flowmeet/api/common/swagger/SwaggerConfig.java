package kr.flowmeet.api.common.swagger;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OperationCustomizer apiErrorCodeOperationCustomizer() {
        return new ApiErrorCodeOperationCustomizer();
    }
}
