package com.menuchallenge.challenge08.security;

import com.menuchallenge.challenge08.dto.responses.JwTClaims;
import com.menuchallenge.challenge08.entity.User;
import com.menuchallenge.challenge08.service.JwTService;
import com.menuchallenge.challenge08.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

	final String AUTH_HEADER = "Authorization";
	private final JwTService jwTService;
	private final UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		try{
			String bearerToken = request.getHeader(AUTH_HEADER);

			if(bearerToken != null && jwTService.verifyJwtTokem(bearerToken)){

				JwTClaims decodeJwT = jwTService.getClaimsByToken(bearerToken);

				User userBySub = userService.getByUserId(decodeJwT.getUserAccountId());

				UsernamePasswordAuthenticationToken authentication= new UsernamePasswordAuthenticationToken(
						userBySub.getUsername(),
						null,
						userBySub.getAuthorities()
				);
				authentication.setDetails(new WebAuthenticationDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}catch (Exception e){

		}

		filterChain.doFilter(request,response);
	}
}
