package com.gastroventure.lion.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Aspect
@Component
public class MemberIdInjectionAspect {

    @Around("execution(* com.gastroventure.lion..controller.*Controller.*(..))")
    public Object injectMemberId(ProceedingJoinPoint joinPoint) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object[] args = joinPoint.getArgs();

        // JWT로 인증이 완료 되지 않았을 경우
        // AnonymousAuthenticationFilter에서 이전 체인에서 생성된 Authentication이 없으면
        // AnonymousAuthenticationToken 생성 하기 때문에 분기처리
        if (authentication instanceof AnonymousAuthenticationToken) {
            return joinPoint.proceed(args);
        }

//        AccessMember accessMember = (AccessMember) authentication.getPrincipal();
//        Long memberId = accessMember.getMemberId();

        Long memberId=1L; //임시방편

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        for (int index = 0; index < methodSignature.getParameterNames().length; index++) {
            String parameter = methodSignature.getParameterNames()[index];
            Class parameterType = methodSignature.getParameterTypes()[index];
            Object arg = args[index];

            if ("memberId".equals(parameter) && parameterType == Long.class) {
                args[index] = memberId;
            } else {
                try {
                    if (arg == null) {
                        arg = parameterType.getDeclaredConstructor().newInstance();
                        args[index] = arg;
                    }
                    Method method = arg.getClass().getMethod("setMemberId", Long.class);
                    method.invoke(arg, memberId);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                }
            }
        }

        return joinPoint.proceed(args);
    }

}
