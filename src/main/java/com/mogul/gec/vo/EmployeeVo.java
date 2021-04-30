package com.mogul.gec.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeVo {
    public int limit;

    public int size;

    public String deptId;

    public String name;
}
