package com.mall.logging.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.mall.logging.AccessLoggerInfo;

@AllArgsConstructor
@Getter
public class AccessLoggerAfterEvent {

    private AccessLoggerInfo logger;
}
