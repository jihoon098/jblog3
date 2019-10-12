package kr.co.jblog.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD}) //이 어노테이션 어디에다가 붙일것이야? 타켓이 어디냐?
//어노테이션 인터페이스
public @interface Auth {
}
