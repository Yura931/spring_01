package org.zerock.domain;

import lombok.Data;


@Data // Getter, Setter 어노테이션만 사용하면 toString메소드는 만들어지지 않음 

// @Getter
// @Setter
// @ToString 이 세가지를 다 정의해주는 것이 @Data어노테이션, Getter와 Setter만 정의해두면 재정의되지않은 toString값이 나온다.
public class SampleDTO {
	private String name;
	private int age;
}
