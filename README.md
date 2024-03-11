#Jwt Filter Implementation


#config/JwtFilter
Extends OncePerRequestFilter and overrided doFilterInternal to retrieve the request and extract the token from the http request.
Then validate the token using JwtUtil and UserDetailsService. Binded the userDetails with UsernamePasswordAuthenticationToken and 
upload it into SecurityContextHolder. Finally bind the UsernamePasswordAuthenticationToken with filterChain.

#config/SecurityConfig

Implemented filterChain for add the JwtFilter before the UsernamePasswordAuthenticationFilter.

#util/JwtUtil
Using JJwt package to implement functions generateJwtToken,getUsernameFromJWT, getUserTypeFromJWT, validateToken for helping generating token and validation.
