package dev.fvames.apidesign.headerapiversion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请求头路由 api demo
 * 判断 header 是否包含指定的版本设置，dev.fvames.apidesign.headerapiversion.APIVersionCondition#getMatchingCondition(javax.servlet.http.HttpServletRequest)
 * version1(): header {X-API-VERSION: v1}
 */
@Profile(value = "header-api-version")
@Slf4j
@RequestMapping("head-api-version")
@RestController
@APIVersion("v1")
public class HeaderAPIVersoinController {

    @GetMapping(value = "/api/user1")
    public int version1() {
        return 1;
    }

    @GetMapping(value = "/api/user2")
    @APIVersion("v2")
    public int version2() {
        return 2;
    }
}
