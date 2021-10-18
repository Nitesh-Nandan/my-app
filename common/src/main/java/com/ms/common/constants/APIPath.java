package com.ms.common.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class APIPath {
    public static final String HEALTH_CHECK = "/health";
    public static final String BASE_PATH = "api/v1";

    @UtilityClass
    public static class SMS {
        public static final String MESSAGE = "/sms/message";
    }

    @UtilityClass
    public static class TEL {
        public static final String MESSAGE = "/tel/message";
    }
}
