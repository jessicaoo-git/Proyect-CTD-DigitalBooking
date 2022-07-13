package com.digitalbooking.backend.Security.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRol is a Querydsl query type for Rol
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRol extends EntityPathBase<Rol> {

    private static final long serialVersionUID = -1142256776L;

    public static final QRol rol = new QRol("rol");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final EnumPath<com.digitalbooking.backend.Security.enums.RolNombre> rolNombre = createEnum("rolNombre", com.digitalbooking.backend.Security.enums.RolNombre.class);

    public QRol(String variable) {
        super(Rol.class, forVariable(variable));
    }

    public QRol(Path<? extends Rol> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRol(PathMetadata metadata) {
        super(Rol.class, metadata);
    }

}

