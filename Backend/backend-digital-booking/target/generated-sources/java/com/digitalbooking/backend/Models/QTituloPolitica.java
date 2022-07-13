package com.digitalbooking.backend.Models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTituloPolitica is a Querydsl query type for TituloPolitica
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTituloPolitica extends EntityPathBase<TituloPolitica> {

    private static final long serialVersionUID = 2029916082L;

    public static final QTituloPolitica tituloPolitica1 = new QTituloPolitica("tituloPolitica1");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final SetPath<Politica, QPolitica> politicas = this.<Politica, QPolitica>createSet("politicas", Politica.class, QPolitica.class, PathInits.DIRECT2);

    public final EnumPath<com.digitalbooking.backend.Models.Enums.EnumTituloPolitica> tituloPolitica = createEnum("tituloPolitica", com.digitalbooking.backend.Models.Enums.EnumTituloPolitica.class);

    public QTituloPolitica(String variable) {
        super(TituloPolitica.class, forVariable(variable));
    }

    public QTituloPolitica(Path<? extends TituloPolitica> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTituloPolitica(PathMetadata metadata) {
        super(TituloPolitica.class, metadata);
    }

}

