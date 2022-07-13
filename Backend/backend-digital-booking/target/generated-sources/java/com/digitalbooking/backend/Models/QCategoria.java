package com.digitalbooking.backend.Models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategoria is a Querydsl query type for Categoria
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategoria extends EntityPathBase<Categoria> {

    private static final long serialVersionUID = -726559383L;

    public static final QCategoria categoria = new QCategoria("categoria");

    public final StringPath descripcion = createString("descripcion");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final SetPath<Producto, QProducto> productos = this.<Producto, QProducto>createSet("productos", Producto.class, QProducto.class, PathInits.DIRECT2);

    public final StringPath titulo = createString("titulo");

    public final StringPath urlImagen = createString("urlImagen");

    public QCategoria(String variable) {
        super(Categoria.class, forVariable(variable));
    }

    public QCategoria(Path<? extends Categoria> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategoria(PathMetadata metadata) {
        super(Categoria.class, metadata);
    }

}

