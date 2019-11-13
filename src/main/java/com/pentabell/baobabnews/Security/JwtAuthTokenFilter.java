package com.pentabell.baobabnews.Security;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pentabell.baobabnews.Repositories.RoleRepository;
import com.pentabell.baobabnews.model.Role;
import com.pentabell.baobabnews.model.RoleName;
import com.pentabell.baobabnews.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider tokenProvider;

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Autowired
    private RoleRepository roleRepository ;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        UsernamePasswordAuthenticationToken authentication = null ;
        try {
            Set<Role> roles = new HashSet<>();
            String jwt = getJwt(request);
            String username = tokenProvider.getUserNameFromJwtToken(jwt);
            System.out.println("Tokenizer => "+username);
            String virtualSessionId = tokenProvider.getVirtualSessionIdFromJwtToken(jwt);
            if (jwt != null && tokenProvider.validateJwtToken(jwt)) {
                Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                //IHRUser userhr = PatchApplication.getSession().retrieveUser(virtualSessionId);
                Users user = new Users(username);
                roles.add(adminRole);
                user.setRoles(roles);
                UserDetails userDetails = UserPrinciple.build(user) ;
                authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Can NOT set user authentication -> Message: {}", e);
            System.out.println("******************"+e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    public String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }

        return null;
    }
}