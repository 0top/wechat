package top.zerotop.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.zerotop.domain.menu.Menu;
import top.zerotop.global.constrant.URLConstrant;
import top.zerotop.util.JsonUtils;
import top.zerotop.util.RestfulWapper;
import top.zerotop.util.Result;
import top.zerotop.wechat.TokenThread;

import java.io.IOException;
import java.util.Map;

@RestController
@Api(value = "公众号菜单管理", description = "公众号菜单管理")
@RequestMapping(value = "/menu", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class MenuController extends BaseController{
    private final static Logger logger = LoggerFactory.getLogger(MenuController.class);

    @GetMapping("/get")
    @ApiOperation(value = "获取当前公众号菜单")
    public Result<Menu> getMenu() {
        String url = URLConstrant.URL_MENU_GET + TokenThread.accessToken.getAccessToken();
        String res = (String)RestfulWapper.getWapper(url).get("result");
        Gson gson = new Gson();
        System.out.println(res);
        System.out.println(JsonUtils.fromJson(res, Menu.class).toString());
        return Result.make(gson.fromJson(res, Menu.class));
    }

    @GetMapping("/delete")
    @ApiOperation(value = "获取当前公众号菜单")
    public Result<String> deleteMenu() {
        String url = URLConstrant.URL_MENU_DELETE + TokenThread.accessToken.getAccessToken();
        String res = (String)RestfulWapper.getWapper(url).get("result");
        return Result.make(res);
    }

    @PostMapping("/create")
    @ApiOperation(value = "创建菜单")
    public Result<String> createMenu(@ApiParam(value = "菜单")
                            @RequestBody Menu menu) {
        String url = URLConstrant.URL_MENU_CREATE + TokenThread.accessToken.getAccessToken();
        String res = RestfulWapper.postWapper(url, JSON.toJSONString(menu));
        return Result.make(res);
    }


}
