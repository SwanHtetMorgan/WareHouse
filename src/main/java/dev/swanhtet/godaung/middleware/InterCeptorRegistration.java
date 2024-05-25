package dev.swanhtet.godaung.middleware;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterCeptorRegistration implements WebMvcConfigurer {

  private final Interceptor interceptor;

  @Override
  public void addInterceptors(@NonNull InterceptorRegistry registry) {
    registry
        .addInterceptor(interceptor)
        .addPathPatterns("/api/inventory/**", "/api/product/**", "/api/pdf**")
        .excludePathPatterns("/api/auth/**");
  }
}
