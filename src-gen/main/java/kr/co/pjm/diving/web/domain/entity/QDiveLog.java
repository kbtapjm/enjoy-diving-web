package kr.co.pjm.diving.web.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDiveLog is a Querydsl query type for DiveLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDiveLog extends EntityPathBase<DiveLog> {

    private static final long serialVersionUID = 788801134L;

    public static final QDiveLog diveLog = new QDiveLog("diveLog");

    public final kr.co.pjm.diving.web.common.domain.entity.QCommonSysEntity _super = new kr.co.pjm.diving.web.common.domain.entity.QCommonSysEntity(this);

    public final StringPath avgDepth = createString("avgDepth");

    public final StringPath diveActivity = createString("diveActivity");

    public final StringPath diveCurrent = createString("diveCurrent");

    public final DateTimePath<java.util.Date> diveDate = createDateTime("diveDate", java.util.Date.class);

    public final NumberPath<Long> diveInHour = createNumber("diveInHour", Long.class);

    public final NumberPath<Long> diveInMinute = createNumber("diveInMinute", Long.class);

    public final NumberPath<Long> diveNo = createNumber("diveNo", Long.class);

    public final StringPath diveNote = createString("diveNote");

    public final NumberPath<Long> diveOutHour = createNumber("diveOutHour", Long.class);

    public final NumberPath<Long> diveOutMinute = createNumber("diveOutMinute", Long.class);

    public final StringPath divePlace = createString("divePlace");

    public final StringPath divePlanBootsYn = createString("divePlanBootsYn");

    public final StringPath divePlanCameraYn = createString("divePlanCameraYn");

    public final StringPath divePlanEanx = createString("divePlanEanx");

    public final EnumPath<kr.co.pjm.diving.web.common.domain.enumeration.DivePlanExrPtnEnum> divePlanExrPtn = createEnum("divePlanExrPtn", kr.co.pjm.diving.web.common.domain.enumeration.DivePlanExrPtnEnum.class);

    public final StringPath divePlanGlovesYn = createString("divePlanGlovesYn");

    public final StringPath divePlanHoodYn = createString("divePlanHoodYn");

    public final StringPath divePlanKnifeYn = createString("divePlanKnifeYn");

    public final StringPath divePlanLightYn = createString("divePlanLightYn");

    public final StringPath divePlanSmbYn = createString("divePlanSmbYn");

    public final EnumPath<kr.co.pjm.diving.web.common.domain.enumeration.DivePlanToolEnum> divePlanTool = createEnum("divePlanTool", kr.co.pjm.diving.web.common.domain.enumeration.DivePlanToolEnum.class);

    public final StringPath divePlanWeight = createString("divePlanWeight");

    public final StringPath divePoint = createString("divePoint");

    public final StringPath diveSafetyTime = createString("diveSafetyTime");

    public final NumberPath<Long> diveTankEnd = createNumber("diveTankEnd", Long.class);

    public final NumberPath<Long> diveTankStart = createNumber("diveTankStart", Long.class);

    public final StringPath diveTime = createString("diveTime");

    public final StringPath diveType = createString("diveType");

    public final StringPath diveWater = createString("diveWater");

    public final StringPath diveWave = createString("diveWave");

    public final StringPath groundRestHour = createString("groundRestHour");

    public final StringPath groundRestMinute = createString("groundRestMinute");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath maxDepth = createString("maxDepth");

    //inherited
    public final DateTimePath<java.util.Date> regDate = _super.regDate;

    //inherited
    public final StringPath regId = _super.regId;

    public final StringPath temperature = createString("temperature");

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updateId = _super.updateId;

    public final StringPath visibility = createString("visibility");

    public QDiveLog(String variable) {
        super(DiveLog.class, forVariable(variable));
    }

    public QDiveLog(Path<? extends DiveLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDiveLog(PathMetadata metadata) {
        super(DiveLog.class, metadata);
    }

}

