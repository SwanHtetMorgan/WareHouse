package dev.swanhtet.godaung.middleware;

import dev.swanhtet.godaung.Authentication.TokenSerivceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
@Slf4j
public class Interceptor implements HandlerInterceptor {

  private final TokenSerivceImpl tokenSerivce;
  private final UrlDefinition urlDefinition;

  @Override
  public boolean preHandle(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull Object handler)
      throws Exception {

    String AuthenticationHeaderToken = request.getHeader("Authorization");
    if (AuthenticationHeaderToken == null || !tokenChecker(AuthenticationHeaderToken)) {
      response.sendError(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
      return false;
    }
    if (tokenChecker(AuthenticationHeaderToken)) {
      log.trace("Process in the permission Checker");
      String role = PermissionChecker(requestTokenDecryption(AuthenticationHeaderToken));
      log.info(role);
      return urlDefinition.customPreHandler(request, response, role);
    }
    return false;
  }

  // ORDER :2 Expire checking
  private boolean tokenChecker(String TOKEN) {
    List<String> segment = tokenSerivce.tokenSegmentation(requestTokenDecryption(TOKEN));
    return tokenSerivce.dbInfoMatcher(segment);
  }

  // ORDER : 1 Decryption
  private String requestTokenDecryption(String Token) {
    byte[] tokenByte = tokenSerivce.base64ToByte(Token);
    return tokenSerivce.decryption(tokenByte);
  }

  private String PermissionChecker(String Decrypted_TOKEN) {
    List<String> segmentaions = tokenSerivce.tokenSegmentation(Decrypted_TOKEN);
    return tokenSerivce.RoleExtraction(segmentaions);
  }
}
