package com.digitalbooking.backend.Models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCiudad is a Querydsl query type for Ciudad
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCiudad extends EntityPathBase<Ciudad> {

    private static final long serialVersionUID = 1882899426L;

    public static final QCiudad ciudad = new QCiudad("ciudad");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath nombre = createString("nombre");

    public final SetPath<Producto, QProducto> productos = this.<Producto, QProducto>createSet("productos", Producto.class, QProducto.class, PathInits.DIRECT2);

    public QCiudad(String variable) {
        super(Ciudad.class, forVariable(variable));
    }

    public QCiudad(Path<? extends Ciudad> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCiudad(PathMetadata metadata) {
        super(Ciudad.class, metadata);
    }

}

