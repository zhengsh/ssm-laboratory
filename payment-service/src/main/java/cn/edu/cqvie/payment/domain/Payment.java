package cn.edu.cqvie.payment.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Payment implements Serializable {

    private Long id;

    private String serial;
}
