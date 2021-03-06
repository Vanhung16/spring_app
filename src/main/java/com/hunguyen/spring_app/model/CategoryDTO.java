package com.hunguyen.spring_app.model;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO implements Serializable {
    private Long categoryId;
    @NotEmpty
    @Length(min = 5)
    private String name;

    private Boolean isEdit = false;
}
