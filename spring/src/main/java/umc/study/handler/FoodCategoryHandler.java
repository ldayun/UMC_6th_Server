package umc.study.handler;

import umc.study.apiPayload.code.status.ErrorStatus;

public class FoodCategoryHandler extends RuntimeException {

    private final ErrorStatus errorStatus;

    public FoodCategoryHandler(ErrorStatus foodCategoryNotFound) {
        this.errorStatus = foodCategoryNotFound;
    }

    public ErrorStatus getErrorStatus() {
        return errorStatus;
    }
}