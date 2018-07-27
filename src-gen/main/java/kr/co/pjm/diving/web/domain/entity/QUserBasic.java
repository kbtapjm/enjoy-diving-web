package kr.co.pjm.diving.web.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserBasic is a Querydsl query type for UserBasic
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserBasic extends EntityPathBase<UserBasic> {

    private static final long serialVersionUID = -130194079L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserBasic userBasic = new QUserBasic("userBasic");

    public final kr.co.pjm.diving.web.common.domain.entity.QCommonEntity _super = new kr.co.pjm.diving.web.common.domain.entity.QCommonEntity(this);

    public final StringPath country = createString("country");

    public final EnumPath<kr.co.pjm.diving.common.domain.enumeration.GenderEnum> gender = createEnum("gender", kr.co.pjm.diving.common.domain.enumeration.GenderEnum.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath introduce = createString("introduce");

    public final DateTimePath<java.util.Date> loginDate = createDateTime("loginDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath profile = createString("profile");

    //inherited
    public final DateTimePath<java.util.Date> regDate = _super.regDate;

    public final EnumPath<kr.co.pjm.diving.common.domain.enumeration.UserStatusEnum> status = createEnum("status", kr.co.pjm.diving.common.domain.enumeration.UserStatusEnum.class);

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    public final QUser user;

    public QUserBasic(String variable) {
        this(UserBasic.class, forVariable(variable), INITS);
    }

    public QUserBasic(Path<? extends UserBasic> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserBasic(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserBasic(PathMetadata metadata, PathInits inits) {
        this(UserBasic.class, metadata, inits);
    }

    public QUserBasic(Class<? extends UserBasic> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

