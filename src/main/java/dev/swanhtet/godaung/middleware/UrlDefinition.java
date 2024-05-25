package dev.swanhtet.godaung.middleware;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class UrlDefinition {
  private final Map<String, String> URL_MAP;

  public UrlDefinition() {
    URL_MAP = new LinkedHashMap<>();
    URL_MAP.put("/api/pdf/", "ADMIN");
  }

  public String permissionFilter(String URL) {
    return URL_MAP.get(URL);
  }

  public boolean customPreHandler(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull String roleForDecision)
      throws IOException {

    String requestURI = request.getRequestURI();
    System.out.println(requestURI);
    if (URL_MAP.containsKey(requestURI)) {
      String ROLE_DEFINITION = URL_MAP.get(requestURI);
      if (ROLE_DEFINITION.equals(roleForDecision)) {
        response.setStatus(HttpServletResponse.SC_CONTINUE);
        return true;
      }
    }
    return false;
  }
}
