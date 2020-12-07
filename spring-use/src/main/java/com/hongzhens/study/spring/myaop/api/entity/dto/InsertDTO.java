package com.hongzhens.study.spring.myaop.api.entity.dto;

import com.hongzhens.study.spring.myaop.myannotion.ParameterValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: HongZhenSi
 * @date: 2020/11/24
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@ParameterValid
public class InsertDTO {

    private Long id;

    private String name;

    private Integer age;
}
