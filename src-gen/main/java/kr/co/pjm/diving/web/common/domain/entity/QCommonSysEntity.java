package kr.co.pjm.diving.web.common.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommonSysEntity is a Querydsl query type for CommonSysEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QCommonSysEntity extends EntityPathBase<CommonSysEntity> {

    private static final long serialVersionUID = 533700226L;

    public static final QCommonSysEntity commonSysEntity = new QCommonSysEntity("commonSysEntity");

    public final DateTimePath<java.util.Date> regDate = createDateTime("regDate", java.util.Date.class);

    public final StringPath regId = createString("regId");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public final StringPath updateId = createString("updateId");

    public QCommonSysEntity(String variable) {
        super(CommonSysEntity.class, forVariable(variable));
    }

    public QCommonSysEntity(Path<? extends CommonSysEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommonSysEntity(PathMetadata metadata) {
        super(CommonSysEntity.class, metadata);
    }

}

