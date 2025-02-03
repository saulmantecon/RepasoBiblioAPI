package org.example.hotelapi2.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.hotelapi2.model.Hotel;
import org.example.hotelapi2.model.User;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.example.hotelapi2.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {

    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    /*
    El método login(...) interceptará las peticiones POST al endpoint /user
    y recibirá como parámetros el usuario y contraseña.
    Como se puede observar, para este ejemplo no se realiza ninguna validación de usuario y contraseña,
    por lo que para cualquier valor de dichos parámetros dejaremos paso. Obviamente, para un proyecto real,
    en este punto deberíamos autenticar el usuario contra nuestra base de datos
    o contra cualquier proveedor externo.
     */

    @PostMapping("user")
    @Operation(summary = "Comprobar usuario", description = "comprobar si el usuario está en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "usuario existe"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    public ResponseEntity<?> user(@RequestBody @Parameter(description = "Datos del usuario", example = "{\"username\":\"juan\" \"pwd\":\"juan\"}") @RequestParam("user") String username, @RequestParam("password") String pwd) {
        User user = userService.findByUserAndPassword(username, pwd);
        if (user == null) {
            return new ResponseEntity<>("Usuario no encontrado en la bd",HttpStatus.NOT_FOUND);
        }
        String token = getJWTToken(user.getUsername());
        user.setToken(token);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
    //Utilizamos el método getJWTToken(...) para construir el token,
    // delegando en la clase de utilidad Jwts que incluye información sobre su expiración
    // y un objeto de GrantedAuthority de Spring que, como veremos más adelante,
    // usaremos para autorizar las peticiones a los recursos protegidos.

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}