package com.digitalbooking.backend.Models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReserva is a Querydsl query type for Reserva
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReserva extends EntityPathBase<Reserva> {

    private static final long serialVersionUID = -1448323474L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReserva reserva = new QReserva("reserva");

    public final DateTimePath<java.util.Date> fechaFinal = createDateTime("fechaFinal", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaInicial = createDateTime("fechaInicial", java.util.Date.class);

    public final NumberPath<Integer> horaComienzo = createNumber("horaComienzo", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QProducto producto;

    public final com.digitalbooking.backend.Security.entity.QUsuario usuario;

    public QReserva(String variable) {
        this(Reserva.class, forVariable(variable), INITS);
    }

    public QReserva(Path<? extends Reserva> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReserva(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReserva(PathMetadata metadata, PathInits inits) {
        this(Reserva.class, metadata, inits);
    }

    public QReserva(Class<? extends Reserva> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.producto = inits.isInitialized("producto") ? new QProducto(forProperty("producto"), inits.get("producto")) : null;
        this.usuario = inits.isInitialized("usuario") ? new com.digitalbooking.backend.Security.entity.QUsuario(forProperty("usuario")) : null;
    }

}

