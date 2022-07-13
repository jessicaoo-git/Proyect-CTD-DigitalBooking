package com.digitalbooking.backend.Models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCaracteristica is a Querydsl query type for Caracteristica
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCaracteristica extends EntityPathBase<Caracteristica> {

    private static final long serialVersionUID = -1035655054L;

    public static final QCaracteristica caracteristica = new QCaracteristica("caracteristica");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath titulo = createString("titulo");

    public final StringPath urlImagen = createString("urlImagen");

    public QCaracteristica(String variable) {
        super(Caracteristica.class, forVariable(variable));
    }

    public QCaracteristica(Path<? extends Caracteristica> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCaracteristica(PathMetadata metadata) {
        super(Caracteristica.class, metadata);
    }

}

