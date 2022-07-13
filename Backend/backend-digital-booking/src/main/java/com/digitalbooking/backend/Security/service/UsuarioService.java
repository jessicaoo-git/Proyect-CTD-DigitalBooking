package com.digitalbooking.backend.Security.service;

import com.digitalbooking.backend.Dto.FavoritosDTO;
import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Dto.ProductoDTO;
import com.digitalbooking.backend.Models.Producto;
import com.digitalbooking.backend.Security.entity.Usuario;
import com.digitalbooking.backend.Security.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ObjectMapper mapper;

    public Optional<Usuario> findByNombreUsuario(String username) {
        return usuarioRepository.findByNombreUsuario(username);
    }
    public boolean existEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
    public boolean existUsername(String username){
        return  usuarioRepository.existsByNombreUsuario(username);
    }

    public void save(Usuario user,String siteURL) throws UnsupportedEncodingException, MessagingException {
        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setEnabled(false);
        usuarioRepository.save(user);
        sendVerificationEmail(user, siteURL);
    }

    private void sendVerificationEmail(Usuario user, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "gregotestapi@gmail.com";
        String senderName = "Digital Booking";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Digital Booking.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getNombre());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
    }
    public boolean verify(String verificationCode) {
        Usuario user = usuarioRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            usuarioRepository.save(user);
            return true;
        }
    }

    public void agregarFavorito(FavoritosDTO favoritosDTO){
        Usuario user = usuarioRepository.findById(favoritosDTO.getUsuario_id()).get();
        Producto producto = new Producto();
        producto.setId(favoritosDTO.getProducto_id().getId());
        user.getProductos().add(producto);
        usuarioRepository.save(user);
    }
    public Integer deleteFavorito(Integer usuario_id, Integer producto_id){
        return usuarioRepository.deleteFavorito(usuario_id,producto_id);
    }
    public PaginaDTO<ProductoDTO> findAllfavoritos(Integer page, Integer size, Integer id) {
        page=page==null?0:page;
        size=size==null?8:size;
        Pageable pageRequest= PageRequest.of(page,size);
        Page<Producto> pagina=usuarioRepository.findAllfavoritos(id,pageRequest);
        List<Producto> contenido = pagina.getContent();
        List<ProductoDTO> listaProductosDto=
                contenido.stream().map(this::mapDTO).collect(Collectors.toList());
        long numeroProductos = pagina.getTotalElements();
        return new PaginaDTO<>(page,size,numeroProductos,listaProductosDto);
    }
    public Integer findFavorito(Integer usuario_id, Integer producto_id) {
       return usuarioRepository.findFavorito(usuario_id,producto_id);
    }
    private ProductoDTO mapDTO(Producto producto){
        return mapper.convertValue(producto, ProductoDTO.class);
    }

    public Producto mapEntity(ProductoDTO productoDTO){
        return mapper.convertValue(productoDTO, Producto.class);
    }
}
