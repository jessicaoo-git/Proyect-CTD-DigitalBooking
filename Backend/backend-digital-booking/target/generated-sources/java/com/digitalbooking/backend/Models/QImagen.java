package com.digitalbooking.backend.Models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QImagen is a Querydsl query type for Imagen
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImagen extends EntityPathBase<Imagen> {

    private static final long serialVersionUID = 2057775613L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QImagen imagen = new QImagen("imagen");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QProducto producto;

    public final StringPath titulo = createString("titulo");

    public final StringPath url = createString("url");

    public QImagen(String variable) {
        this(Imagen.class, forVariable(variable), INITS);
    }

    public QImagen(Path<? extends Imagen> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QImagen(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QImagen(PathMetadata metadata, PathInits inits) {
        this(Imagen.class, metadata, inits);
    }

    public QImagen(Class<? extends Imagen> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.producto = inits.isInitialized("producto") ? new QProducto(forProperty("producto"), inits.get("producto")) : null;
    }

}

