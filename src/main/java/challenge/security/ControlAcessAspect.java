package challenge.security;

import challenge.exception.types.ChallengeControlAcessException;
import challenge.exception.types.ChallengeServiceException;
import challenge.services.UserService;
import challenge.utils.ErrorMessages;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Created by rsanchpa on 29/09/2017.
 */

@Aspect
@Component
@EnableConfigurationProperties
public class ControlAcessAspect {

    @Autowired
    UserService userService;

    @Autowired
    ErrorMessages errorMessages;


    @Around("@annotation(ControlAccessCharacter)")
    public Object ckeckUserActive(ProceedingJoinPoint joinPoint) throws ChallengeControlAcessException, Throwable {
        String userName = joinPoint.getArgs()[0].toString();
        try {
            this.userService.isActiveUser(userName);
        } catch (ChallengeServiceException e) {
            throw new ChallengeControlAcessException(e.getMessage());
        }
        return joinPoint.proceed();
    }

    @Around("@annotation(ControlAccessUser)")
    public Object ckeckUserActiveAdmin(ProceedingJoinPoint joinPoint) throws ChallengeControlAcessException, Throwable {
        String userName = joinPoint.getArgs()[0].toString();
        try {
            this.userService.isAdminAndActiveUser(userName);
        } catch (ChallengeServiceException e) {
            throw new ChallengeControlAcessException(e.getMessage());
        }
        return joinPoint.proceed();
    }

//    @Around("@annotation(ControlAccessCharacter)")
//    public Object beforeSampleCreation(ProceedingJoinPoint joinPoint) throws Throwable {
//        long start = System.currentTimeMillis();
//
//        Object proceed = joinPoint.proceed();
//
//        long executionTime = System.currentTimeMillis() - start;
//
//        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
//        return proceed;
//    }
}
