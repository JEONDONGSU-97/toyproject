package My.toyproject.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//우리가 정의한 ValidEnum 어노테이션 인터페이스와 우리가 Validation에 사용할 Type은 Enum class type이다.
public class EnumValidator implements ConstraintValidator<ValidEnum, Enum> {

    private ValidEnum annotaion;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        this.annotaion = constraintAnnotation;
    }

    //실제 Validation에 사용할 코드
    @Override
    public boolean isValid(Enum value, ConstraintValidatorContext context) {
        boolean result = false;
        Object[] enumValues = this.annotaion.enumClass().getEnumConstants();
        if (enumValues != null) {
            for (Object enumValue : enumValues) {
                if (value == enumValue) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
