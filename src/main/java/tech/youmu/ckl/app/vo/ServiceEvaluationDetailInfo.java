package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "服务评价明细")
public class ServiceEvaluationDetailInfo {

    @ApiModelProperty(value="评价名称")
    private String evaluationName;

    @ApiModelProperty(value="评价星级")
    private int evaluationStar;

    public String getEvaluationName() {
        return evaluationName;
    }

    public void setEvaluationName(String evaluationName) {
        this.evaluationName = evaluationName;
    }

    public int getEvaluationStar() {
        return evaluationStar;
    }

    public void setEvaluationStar(int evaluationStar) {
        this.evaluationStar = evaluationStar;
    }

    
    
    
}