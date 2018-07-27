package kr.co.pjm.diving.web.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRole is a Querydsl query type for Role
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRole extends EntityPathBase<Role> {

    private static final long serialVersionUID = 1183075192L;

    public static final QRole role1 = new QRole("role1");

    public final kr.co.pjm.diving.web.common.domain.entity.QCommonEntity _super = new kr.co.pjm.diving.web.common.domain.entity.QCommonEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> regDate = _super.regDate;

    public final EnumPath<kr.co.pjm.diving.common.domain.enumeration.RoleTypeEnum> role = createEnum("role", kr.co.pjm.diving.common.domain.enumeration.RoleTypeEnum.class);

    public final StringPath roleName = createString("roleName");

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    public final SetPath<UserRole, QUserRole> userRoles = this.<UserRole, QUserRole>createSet("userRoles", UserRole.class, QUserRole.class, PathInits.DIRECT2);

    public QRole(String variable) {
        super(Role.class, forVariable(variable));
    }

    public QRole(Path<? extends Role> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRole(PathMetadata metadata) {
        super(Role.class, metadata);
    }

}

