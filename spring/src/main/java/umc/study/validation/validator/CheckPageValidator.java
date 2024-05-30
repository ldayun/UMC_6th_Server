package umc.study.validation.validator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.service.StoreService.StoreQueryService;
import umc.study.validation.annotation.CheckPage;

import java.util.Optional;
@Component
@RequiredArgsConstructor
public class CheckPageValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        if (value < 0){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.INVALID_PAGE.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}