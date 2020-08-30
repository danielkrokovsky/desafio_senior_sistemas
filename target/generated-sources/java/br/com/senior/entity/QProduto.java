package br.com.senior.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProduto is a Querydsl query type for Produto
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProduto extends EntityPathBase<Produto> {

    private static final long serialVersionUID = -1236908415L;

    public static final QProduto produto = new QProduto("produto");

    public final BooleanPath ativo = createBoolean("ativo");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nome = createString("nome");

    public final BooleanPath servico = createBoolean("servico");

    public final NumberPath<Double> valor = createNumber("valor", Double.class);

    public QProduto(String variable) {
        super(Produto.class, forVariable(variable));
    }

    public QProduto(Path<? extends Produto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduto(PathMetadata metadata) {
        super(Produto.class, metadata);
    }

}

