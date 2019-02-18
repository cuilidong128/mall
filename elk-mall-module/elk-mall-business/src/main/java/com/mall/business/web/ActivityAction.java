package com.mall.business.web;

import com.mall.business.service.ActivityService;
import com.mall.common.util.JsonResponse;
import com.mall.common.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author cuilidong
 * @date 2019/2/12 8:49
 */
@RestController
@RequestMapping("/activity")
public class ActivityAction {
    @Autowired
    private ActivityService activityService;

    @PostMapping("/part")
    public void list() {
        Long number = activityService.activityPartNumber(1);
        System.out.println("part number" + number);
    }
}
