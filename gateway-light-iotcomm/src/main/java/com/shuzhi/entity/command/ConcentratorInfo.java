package com.shuzhi.entity.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 集中器详情
 * @Author:     lirb
 * @CreateDate:   2019/8/11 10:46
 * @Version:   1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConcentratorInfo {

    @JsonProperty("ccuID_")
    private Integer ccuID;
    private String ccuUid;
    private Integer mode;
    private String modelName;
    private String name;
    private String sim;
    private Double latitude;
    private Double longitude;
    private String result;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"ccuID\":\"")
                .append(ccuID).append('\"');
        sb.append(",\"ccuUid\":")
                .append(ccuUid);
        sb.append(",\"mode\":")
                .append(mode);
        sb.append(",\"modelName\":")
                .append(modelName);
        sb.append(",\"name\":")
                .append(name);
        sb.append(",\"sim\":")
                .append(sim);
        sb.append(",\"latitude\":")
                .append(latitude);
        sb.append(",\"longitude\":")
                .append(longitude);
        sb.append(",\"result\":")
                .append(result);
        sb.append('}');
        return sb.toString();
    }
}
