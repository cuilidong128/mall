package com.mall.logging.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.mall.logging.AccessLoggerInfo;

@AllArgsConstructor
@Getter
public class AccessLoggerBeforeEvent {

    private AccessLoggerInfo logger;
}
