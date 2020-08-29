package br.com.senior.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPedido is a Querydsl query type for Pedido
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPedido extends EntityPathBase<Pedido> {

    private static final long serialVersionUID = 1056149181L;

    public static final QPedido pedido = new QPedido("pedido");

    public final StringPath id = createString("id");

    public final BooleanPath situacao = createBoolean("situacao");

    public QPedido(String variable) {
        super(Pedido.class, forVariable(variable));
    }

    public QPedido(Path<? extends Pedido> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPedido(PathMetadata metadata) {
        super(Pedido.class, metadata);
    }

}

