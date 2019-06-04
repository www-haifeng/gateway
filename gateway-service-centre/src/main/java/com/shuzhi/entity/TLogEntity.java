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
 * Created by ztt on 2019/6/3
 **/
@Data
@Entity
@Table(name = "t_log")
@TypeDefs({ @TypeDef(name = "StringJsonObject", typeClass = StringJsonUserType.class) })
public class TLogEntity {
    @Id
    private int id;

    @Column(name = "server_type")
    private Integer serverType;

    @Column(name = "timestamp")
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date timestamp;

    @Column(name = "log_type")
    private String logType;

    @Type(type = "StringJsonObject")
    @Column(name = "data")
    private String data;

    @Column(name = "describe")
    private String describe;

}
