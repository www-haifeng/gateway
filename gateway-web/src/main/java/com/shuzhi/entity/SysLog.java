package com.shuzhi.entity;

import com.shuzhi.util.StringJsonUserType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
* @Program: SysLog
* @Description:
* @Author: YuJQ
* @Create: 2019/6/25 17:06
**/
@Data
@Entity
@Table(name = "t_log")
@TypeDefs({ @TypeDef(name = "StringJsonObject", typeClass = StringJsonUserType.class) })
public class SysLog {

    @Id
    @GeneratedValue
    private int id;
    //服务类型 0系统日志 1接口日志
    @Column(name = "server_type")
    private Integer serverType;

    @Column(name = "timestamp")
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date timestamp;

  //0命令日志/1监控日志/2告警日志等/3系统日志
    @Column(name = "log_type")
    private String logType;

    @Type(type = "StringJsonObject")
    @Column(name = "data")
    private String data;

    @Column(name = "describe")
    private String describe;
}
