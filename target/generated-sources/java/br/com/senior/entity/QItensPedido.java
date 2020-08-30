package br.com.senior.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItensPedido is a Querydsl query type for ItensPedido
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItensPedido extends EntityPathBase<ItensPedido> {

    private static final long serialVersionUID = 2007350156L;

    public static final QItensPedido itensPedido = new QItensPedido("itensPedido");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Produto, QProduto> produtos = this.<Produto, QProduto>createList("produtos", Produto.class, QProduto.class, PathInits.DIRECT2);

    public final BooleanPath status = createBoolean("status");

    public QItensPedido(String variable) {
        super(ItensPedido.class, forVariable(variable));
    }

    public QItensPedido(Path<? extends ItensPedido> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItensPedido(PathMetadata metadata) {
        super(ItensPedido.class, metadata);
    }

}

