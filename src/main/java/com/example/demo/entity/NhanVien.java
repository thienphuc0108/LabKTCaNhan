package com.example.demo.entity;

import com.example.demo.validator.annotation.ValidPhongBanId;
import com.example.demo.validator.annotation.ValidUserId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="NhanVien")
public class NhanVien {
    @Id
    @Column(name = "Ma_NV")
    @NotEmpty(message = "Mã NV không được để trống")
    @Size(max = 3, message = "Mã NV phải đủ 3 kí tự")
    private String Ma_NV;
    @Column(name = "Ten_NV")
    @NotEmpty(message = "Tên NV không được để trống")
    @Size(max = 100, min = 1, message = "Tên NV phải <100 kí tự")
    private String Ten_NV;
    @Column(name = "Phai")
    @Size(max = 3, min = 1, message = "Phái phải có 3 kí tự")
    private String Phai;
    @Column(name = "Noi_Sinh")
    @Size(max = 200, min = 1, message = "Nơi sinh phải <200 kí tự")
    private String Noi_Sinh;
    @Column(name = "Luong")
    private int Luong;

    @ManyToOne
    @JoinColumn(name = "Ma_Phong")
    @ValidPhongBanId
    private PhongBan PhongBan;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @ValidUserId
    private User user;
}
