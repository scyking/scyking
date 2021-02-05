package com.scyking.slog.util;

import com.scyking.slog.pojo.SysLogPageVO;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 统一构建查询条件
 *
 * @author scyking
 **/
@Component
public class QueryUtils {

    public Query build(SysLogPageVO sysLogVO) {
        Criteria criteria = Criteria.where("createTime")
                .gte(sysLogVO.getFromCreateTime() == null ? LocalDateTime.MIN : sysLogVO.getFromCreateTime())
                .lte(sysLogVO.getToCreateTime() == null ? LocalDateTime.MAX : sysLogVO.getToCreateTime());
        if (sysLogVO.getLogType() != null) {
            criteria.and("logType").is(sysLogVO.getLogType());
        }
        if (sysLogVO.getOptType() != null) {
            criteria.and("optType").is(sysLogVO.getOptType());
        }
        return new Query(criteria);
    }

    public Query buildPage(SysLogPageVO sysLogVO) {
        return build(sysLogVO)
                .skip((sysLogVO.getPageNumber() - 1) * sysLogVO.getPageSize())
                .limit(sysLogVO.getPageSize());
    }

}
