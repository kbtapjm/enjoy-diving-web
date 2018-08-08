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

    public final NumberPath<Long> avgDepth = createNumber("avgDepth", Long.class);

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

    public final NumberPath<Long> divePlanEanx = createNumber("divePlanEanx", Long.class);

    public final StringPath divePlanExrPtn = createString("divePlanExrPtn");

    public final StringPath divePlanGlovesYn = createString("divePlanGlovesYn");

    public final StringPath divePlanHoodYn = createString("divePlanHoodYn");

    public final StringPath divePlanKnifeYn = createString("divePlanKnifeYn");

    public final StringPath divePlanLightYn = createString("divePlanLightYn");

    public final StringPath divePlanSmbYn = createString("divePlanSmbYn");

    public final StringPath divePlanTool = createString("divePlanTool");

    public final NumberPath<Long> divePlanWeight = createNumber("divePlanWeight", Long.class);

    public final StringPath divePoint = createString("divePoint");

    public final NumberPath<Long> diveSafetyTime = createNumber("diveSafetyTime", Long.class);

    public final NumberPath<Long> diveTankEnd = createNumber("diveTankEnd", Long.class);

    public final NumberPath<Long> diveTankStart = createNumber("diveTankStart", Long.class);

    public final NumberPath<Long> diveTime = createNumber("diveTime", Long.class);

    public final StringPath diveType = createString("diveType");

    public final StringPath diveWater = createString("diveWater");

    public final StringPath diveWave = createString("diveWave");

    public final NumberPath<Long> groundRestHour = createNumber("groundRestHour", Long.class);

    public final NumberPath<Long> groundRestMinute = createNumber("groundRestMinute", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> maxDepth = createNumber("maxDepth", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> regDate = _super.regDate;

    //inherited
    public final StringPath regId = _super.regId;

    public final NumberPath<Long> temperature = createNumber("temperature", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> updateDate = _super.updateDate;

    //inherited
    public final StringPath updateId = _super.updateId;

    public final NumberPath<Long> visibility = createNumber("visibility", Long.class);

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

