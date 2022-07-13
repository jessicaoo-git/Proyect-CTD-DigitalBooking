package com.digitalbooking.backend.Security.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUsuario is a Querydsl query type for Usuario
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUsuario extends EntityPathBase<Usuario> {

    private static final long serialVersionUID = 175818839L;

    public static final QUsuario usuario = new QUsuario("usuario");

    public final StringPath apellido = createString("apellido");

    public final StringPath email = createString("email");

    public final BooleanPath enabled = createBoolean("enabled");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath nombre = createString("nombre");

    public final StringPath nombreUsuario = createString("nombreUsuario");

    public final StringPath password = createString("password");

    public final SetPath<Rol, QRol> roles = this.<Rol, QRol>createSet("roles", Rol.class, QRol.class, PathInits.DIRECT2);

    public final StringPath verificationCode = createString("verificationCode");

    public QUsuario(String variable) {
        super(Usuario.class, forVariable(variable));
    }

    public QUsuario(Path<? extends Usuario> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUsuario(PathMetadata metadata) {
        super(Usuario.class, metadata);
    }

}

