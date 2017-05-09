package kr.co.pjm.diving.web.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserDive is a Querydsl query type for UserDive
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserDive extends EntityPathBase<UserDive> {

    private static final long serialVersionUID = -1943795103L;

    public static final QUserDive userDive = new QUserDive("userDive");

    public final kr.co.pjm.diving.web.common.domain.entity.QCommonEntity _super = new kr.co.pjm.diving.web.common.domain.entity.QCommonEntity(this);

    public final StringPath diveGroup = createString("diveGroup");

    public final StringPath diveLevel = createString("diveLevel");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DatePath<java.util.Date> regDate = _super.regDate;

    public final StringPath signature = createString("signature");

    public final StringPath team = createString("team");

    //inherited
    public final DatePath<java.util.Date> updateDate = _super.updateDate;

    public QUserDive(String variable) {
        super(UserDive.class, forVariable(variable));
    }

    public QUserDive(Path<? extends UserDive> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserDive(PathMetadata metadata) {
        super(UserDive.class, metadata);
    }

}

