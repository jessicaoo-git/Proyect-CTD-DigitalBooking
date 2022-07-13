package com.digitalbooking.backend.Models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPolitica is a Querydsl query type for Politica
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPolitica extends EntityPathBase<Politica> {

    private static final long serialVersionUID = -1050568615L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPolitica politica = new QPolitica("politica");

    public final StringPath descripcion = createString("descripcion");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final SetPath<Producto, QProducto> productos = this.<Producto, QProducto>createSet("productos", Producto.class, QProducto.class, PathInits.DIRECT2);

    public final QTituloPolitica titulo;

    public QPolitica(String variable) {
        this(Politica.class, forVariable(variable), INITS);
    }

    public QPolitica(Path<? extends Politica> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPolitica(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPolitica(PathMetadata metadata, PathInits inits) {
        this(Politica.class, metadata, inits);
    }

    public QPolitica(Class<? extends Politica> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.titulo = inits.isInitialized("titulo") ? new QTituloPolitica(forProperty("titulo")) : null;
    }

}

