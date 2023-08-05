package com.turkcell.socceronlinemanagement.security.filter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService { //jwt oluşturma, geçerlilik kontorlu gibi işlemler için

    @Value("${application.security.jwt.secret-key}")
    private String secretKey; //eğer secretKey değeri olmasaydı, JWT'lerin doğrulanması mümkün olmazdı (JWT'lerin doğrulanması için gerekli olan şifreleme anahtarı)
    public String extractUsername(String token) { //token'dan kullanıcı adını çıkarmak için
        return extractClaim(token,Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {  //extractClaim belirli bir isteği çıkarmak için (genel metot)
        final Claims claims = extractAllClaims(token); //token'dan tüm istekleri çıkarmak için
        return claimsResolver.apply(claims);
    }
    public String generateToken(UserDetails userDetails){ // userDetails den bir JWT oluşturmak için (kullanıcı adı, rolleri, vb.)
        return generateToken(new HashMap<>(),userDetails);

        // JWT'ye eklenmek için talepleri içeren bir Map oluşturdun
    }
    public String generateToken(  //jwt oluşturmak için
            Map<String,Object> extractClaims,
            UserDetails userDetails
    ){

        return Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis())) //şu an
                .setExpiration(new Date(System.currentTimeMillis() + 10000 * 60 *24)) // 10 saat
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) //HS256 algoritması kullanılarak imzalandı
                .compact(); // compact() metodu, JWT'yi bir String'e dönüştürür.
    }

    public boolean isTokenValid(String token, UserDetails userDetails){ //token'ın geçerli olup olmadığını kontrol etmek için
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
        //token'ın kullanıcı adı ile eşleşip eşleşmediğini ve token'ın süresinin dolup dolmadığını kontrol eder
    }
    private boolean isTokenExpired(String token) { //token'ın süresinin dolup dolmadığını kontrol etmek için üstte kullandık
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {  //JWT'den son kullanma tarihini çıkarıyor
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) { //JWT'den tüm talepleri çıkarıyor
        return Jwts
                .parserBuilder() //JWT'yi ayrıştırmak için bir JwtParser oluştur
                .setSigningKey(getSignInKey()) //JWT'yi doğrulamak için kullanılan gizli anahtarı ayarla
                .build() //JwtParser'ı oluştur
                .parseClaimsJws(token) //JWT'yi ayrıştır
                .getBody();
    }

    private Key getSignInKey() { //secret key in alınması için
        byte[] keyBytes = Decoders.BASE64.decode(secretKey); //secretKey'i base64 formatından çözüyoruz
        return Keys.hmacShaKeyFor(keyBytes); //secretKey'i alıyoruz
    }
}
