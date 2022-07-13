package com.digitalbooking.backend.Models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProducto is a Querydsl query type for Producto
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProducto extends EntityPathBase<Producto> {

    private static final long serialVersionUID = 1693236842L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProducto producto = new QProducto("producto");

    public final SetPath<Caracteristica, QCaracteristica> caracteristicas = this.<Caracteristica, QCaracteristica>createSet("caracteristicas", Caracteristica.class, QCaracteristica.class, PathInits.DIRECT2);

    public final QCategoria categoria;

    public final QCiudad ciudad;

    public final StringPath descripcion = createString("descripcion");

    public final BooleanPath disponible = createBoolean("disponible");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final SetPath<Imagen, QImagen> imagenes = this.<Imagen, QImagen>createSet("imagenes", Imagen.class, QImagen.class, PathInits.DIRECT2);

    public final SetPath<Politica, QPolitica> politicas = this.<Politica, QPolitica>createSet("politicas", Politica.class, QPolitica.class, PathInits.DIRECT2);

    public final SetPath<Reserva, QReserva> reservas = this.<Reserva, QReserva>createSet("reservas", Reserva.class, QReserva.class, PathInits.DIRECT2);

    public final StringPath titulo = createString("titulo");

    public QProducto(String variable) {
        this(Producto.class, forVariable(variable), INITS);
    }

    public QProducto(Path<? extends Producto> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProducto(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProducto(PathMetadata metadata, PathInits inits) {
        this(Producto.class, metadata, inits);
    }

    public QProducto(Class<? extends Producto> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.categoria = inits.isInitialized("categoria") ? new QCategoria(forProperty("categoria")) : null;
        this.ciudad = inits.isInitialized("ciudad") ? new QCiudad(forProperty("ciudad")) : null;
    }

}

