package com.laonworks.shop.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.laonworks.shop.api.controller.handler.*;
import com.laonworks.shop.api.controller.request.auth.*;
import com.laonworks.shop.api.controller.response.auth.*;
import com.laonworks.shop.api.service.CustomUserDetails;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.multipart.MultipartFile;
import com.laonworks.shop.api.framework.libs.FormFile;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import com.laonworks.shop.api.controller.handler.auth.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController extends BaseController {
  static Logger logger = LoggerFactory.getLogger(AuthController.class);

  @Autowired
  private SignInHandler signInHandler;

  @RequestMapping(method = RequestMethod.POST, value = "signin", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "sing in")
  SignInResponse signIn(@AuthenticationPrincipal Authentication auth, @RequestBody SignInRequest req, HttpServletRequest request) {
    signInHandler.setHttpServletRequest(request);
    CustomUserDetails user = null;
    if (auth != null) {
      user = (CustomUserDetails) auth.getPrincipal();
    }
    if (checkRoute(RequestMethod.POST, "/api/v1/auth/signin", user) == false) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    return signInHandler.execute(req);
  }

  @Autowired
  private SignUpHandler signUpHandler;

  @RequestMapping(method = RequestMethod.POST, value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "sign up")
  SignUpResponse signUp(@AuthenticationPrincipal Authentication auth, @RequestBody SignUpRequest req, HttpServletRequest request) {
    signUpHandler.setHttpServletRequest(request);
    CustomUserDetails user = null;
    if (auth != null) {
      user = (CustomUserDetails) auth.getPrincipal();
    }
    if (checkRoute(RequestMethod.POST, "/api/v1/auth/signup", user) == false) {
      throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
    }
    return signUpHandler.execute(req);
  }
}
