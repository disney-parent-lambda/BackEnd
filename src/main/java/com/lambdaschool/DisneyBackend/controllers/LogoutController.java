package com.lambdaschool.DisneyBackend.controllers;

import com.lambdaschool.DisneyBackend.models.ErrorDetail;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogoutController
{
    @Autowired
    private TokenStore tokenStore;



    @ApiOperation(value = "Retrieves a token associated with the user and revokes token.", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "token revoked", response = void.class),
            @ApiResponse(code = 404, message = "token Not revoked", response = ErrorDetail.class)})
    @RequestMapping(value = "/oauth/revoke-token",
                    method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request)
    {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null)
        {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
        }
    }
}
