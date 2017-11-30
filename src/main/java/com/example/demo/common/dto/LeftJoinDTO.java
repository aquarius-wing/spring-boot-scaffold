
 package com.example.demo.common.dto;



import lombok.Data;

/**
 * @Author wing
 */
@Data
public class LeftJoinDTO {
    private String leftTableName;
    private String leftColumn;
    private String rightTableName;
    private String rightColumn;
    private String targetColumn;
    private Object targetValue;

    public LeftJoinDTO(String leftTableName, String leftColumn, String rightTableName, String rightColumn, String targetColumn, Object targetValue) {
        this.leftTableName = leftTableName;
        this.leftColumn = leftColumn;
        this.rightTableName = rightTableName;
        this.rightColumn = rightColumn;
        this.targetColumn = targetColumn;
        this.targetValue = targetValue;
    }
}

