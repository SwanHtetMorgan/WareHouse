package dev.swanhtet.godaung.middleware;

import dev.swanhtet.godaung.Authentication.TokenSerivceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.UrlPathHelper;

import java.util.List;


@Component
@RequiredArgsConstructor
public class Interceptor implements HandlerInterceptor {

  private  final TokenSerivceImpl tokenSerivce;
  @Override
  public boolean preHandle(
          @NonNull HttpServletRequest request,
          @NonNull HttpServletResponse response,
          @NonNull Object handler)
          throws Exception {

    String AuthenticationHeaderToken = request.getHeader("Authorization");
    if(AuthenticationHeaderToken == null || !tokenChecker(AuthenticationHeaderToken))
    {
      response.sendError(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
      return false;
    }
    response.setStatus(200);
    return true;
  }


  private  boolean tokenChecker(String TOKEN){
    byte[] tokenByte = tokenSerivce.base64ToByte(TOKEN);
    String decryptionProcess = tokenSerivce.decryption(tokenByte);
    List<String> segment  = tokenSerivce.tokenSegmentation(decryptionProcess);
    return tokenSerivce.dbInfoMatcher(segment);
  }


}
