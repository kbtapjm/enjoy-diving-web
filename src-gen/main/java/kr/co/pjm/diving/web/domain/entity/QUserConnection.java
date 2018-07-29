package kr.co.pjm.diving.web.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserConnection is a Querydsl query type for UserConnection
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserConnection extends EntityPathBase<UserConnection> {

    private static final long serialVersionUID = -1089227381L;

    public static final QUserConnection userConnection = new QUserConnection("userConnection");

    public final StringPath accessToken = createString("accessToken");

    public final StringPath displayName = createString("displayName");

    public final NumberPath<Long> expireTime = createNumber("expireTime", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath profileUrl = createString("profileUrl");

    public final StringPath providerId = createString("providerId");

    public final StringPath providerUserId = createString("providerUserId");

    public final NumberPath<Integer> rank = createNumber("rank", Integer.class);

    public final StringPath refreshToken = createString("refreshToken");

    public final StringPath secret = createString("secret");

    public final StringPath userId = createString("userId");

    public QUserConnection(String variable) {
        super(UserConnection.class, forVariable(variable));
    }

    public QUserConnection(Path<? extends UserConnection> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserConnection(PathMetadata metadata) {
        super(UserConnection.class, metadata);
    }

}

