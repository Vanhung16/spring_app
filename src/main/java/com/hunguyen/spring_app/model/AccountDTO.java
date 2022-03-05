package com.hunguyen.spring_app.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class AccountDTO implements Serializable{
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    private Boolean isEdit;
}
