package com.example.tomatomall.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class Account {
    private Integer id;
    
    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, max = 20, message = "用户名长度必须在4-20之间")
    private String username;
    
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, message = "密码长度不能小于6位")
    private String password;
    
    @NotBlank(message = "姓名不能为空")
    private String name;
    
    private String avatar;
    
    @NotBlank(message = "用户角色不能为空")
    private String role;
    
    @Pattern(regexp = "^1\\d{10}$", message = "手机号格式不正确")
    private String telephone;
    
    @Email(message = "邮箱格式不正确")
    private String email;
    
    private String location;
}
