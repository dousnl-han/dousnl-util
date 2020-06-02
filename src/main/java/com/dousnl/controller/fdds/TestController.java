//package com.dousnl.controller.fdds;
//
//import com.soybean.common.annotation.Valid;
//import com.soybean.common.utils.basic.BeanUtil;
//import com.soybean.common.utils.basic.IntegerEncryptTool;
//import com.soybean.common.utils.basic.JsonUtil;
//import com.soybean.common.utils.basic.entities.BusiSystemResponse;
//import com.soybean.common.utils.basic.entities.Page;
//import com.soybean.compose.bo.SourceEventBO;
//import com.soybean.compose.bo.SourceEventVersionBO;
//import com.soybean.compose.bo.adapter.*;
//import com.soybean.compose.service.ISourceEventService;
//import com.soybean.compose.service.ISourceEventVersionService;
//import com.soybean.enums.common.CommonResponseCodeEnum;
//import com.soybean.strace.common.utils.StringUtil;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.apache.dubbo.config.annotation.Reference;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.List;
//import java.util.Objects;
//
///**
// * TODO
// *
// * @author hanliang
// * @version 1.0
// * @date 2020/5/22 17:29
// */
//@RestController
//@RequestMapping("/admin/compose-system")
//public class TestController {
//    private static Logger logger = LoggerFactory.getLogger(TestController.class);
//
//    @Reference
//    private ISourceEventService sourceEventService;
//    @Reference
//    private ISourceEventVersionService sourceEventVersionService;
//
//
//
//    @PostMapping("/v100/addSourceEvent")
//    @ApiOperation(value = "新增活动", notes = "新增活动")
//    @ApiImplicitParams({
//            @ApiImplicitParam(required = true, name = "Cookie", value = "令牌", paramType = "header", dataType = "string")})
//    //@Authenticate(permissions = "addSourceEvent")
//    public BusiSystemResponse<SourceEventBO> addDistributor(@RequestBody @Valid SourceEventBO vo) {
//        logger.info("=========活动容器添加参数，vo={}", JsonUtil.toJSON(vo));
//        BusiSystemResponse<SourceEventBO> response = new BusiSystemResponse<>();
////        vo.setStatus(SourceEventStatusEnum.ENABLE.getCode());
////        vo.setActivityType(SourceEventActivityTypeEnum.ACTIVITY.getCode());
////        vo.setCreateBy(admin.getUserName());
////        vo.setUpdateBy(admin.getUserName());
//        try {
//            SourceEventBO sourceEventBO = BeanUtil.copyProperties(vo, SourceEventBO.class);
//            return sourceEventService.addSourceEvent(sourceEventBO);
//        } catch (Exception e) {
//            logger.error("=========活动容器添加活动失败，vo={}", JsonUtil.toJSON(vo), e);
//            response.setMsg("添加活动失败!");
//            response.setStatus(CommonResponseCodeEnum.ERROR.getCode());
//        }
//        return response;
//    }
//
//    @PostMapping("/v100/updateSourceEvent")
//    @ApiOperation(value = "编辑活动", notes = "编辑活动")
//    @ApiImplicitParams({
//            @ApiImplicitParam(required = true, name = "Cookie", value = "令牌", paramType = "header", dataType = "string")})
//    //@Authenticate(permissions = "updateSourceEvent")
//    public BusiSystemResponse<SourceEventBO> updateSourceEvent(@RequestBody @Valid SourceEventBO vo
//    ) {
//        logger.info("=========活动容器修改参数，vo={}", JsonUtil.toJSON(vo));
//        BusiSystemResponse<SourceEventBO> response = new BusiSystemResponse<>();
//        try {
//            SourceEventBO sourceEventBO = BeanUtil.copyProperties(vo, SourceEventBO.class);
//            return sourceEventService.updateSourceEvent(sourceEventBO);
//        } catch (Exception e) {
//            logger.error("=========活动容器修改活动失败，vo={}", JsonUtil.toJSON(vo), e);
//            response.setMsg("修改活动失败!");
//            response.setStatus(CommonResponseCodeEnum.ERROR.getCode());
//        }
//        return response;
//    }
//
//    @PostMapping("/v100/listSource")
//    @ApiOperation(value = "列表查询活动", notes = "列表查询活动")
//    //@Authenticate(permissions = "listSource")
//    public BusiSystemResponse<List<SourceEventBO>> listSource(@RequestBody SourceEventSearchBO vo,
//                                                                    @RequestBody Page page) {
//        logger.info("=========活动容器查询参数，vo={},page={}", vo, page);
//        BusiSystemResponse<List<SourceEventBO>> response = new BusiSystemResponse<>();
//        try {
//            SourceEventSearchBO sourceEventSearchBO = BeanUtil.copyProperties(vo, SourceEventSearchBO.class);
//            BusiSystemResponse<List<SourceEventBO>> sourceEventResponse = sourceEventService.listSourceEventPage(sourceEventSearchBO, page);
//            if (sourceEventResponse!=null && !CollectionUtils.isEmpty(sourceEventResponse.getData())){
//                response.setPage(sourceEventResponse.getPage());
//                response.setData(sourceEventResponse.getData());
//                response.setStatus(CommonResponseCodeEnum.SUCCESS.getCode());
//                return response;
//            }
//        } catch (Exception e) {
//            logger.error("=========活动容器修改活动失败，vo={}", JsonUtil.toJSON(vo), e);
//            response.setMsg("修改活动失败!");
//            response.setStatus(CommonResponseCodeEnum.ERROR.getCode());
//        }
//        response.setStatus(CommonResponseCodeEnum.DATA_NOT_EXIST.getCode());
//        return response;
//    }
//
//    @GetMapping("/v100/versionUrl")
//    @ApiOperation(value = "活动版本链接", notes = "活动版本链接")
//    //@Authenticate(permissions = "listSource")
//    public BusiSystemResponse<String> creatVersionUrl(
//            @RequestParam(value = "activityId") Integer activityId,
//            @RequestParam(value = "id",required = false) Integer id) {
//        logger.info("=========活动版本链接参数，activityId={},id={}", activityId, id);
//        BusiSystemResponse<String> response = new BusiSystemResponse<>();
//        try {
//            StringBuilder url=new StringBuilder("http://rds-d.dushu.io/compose-orch/v100/sourceurl/?activityId=");
//            url.append(IntegerEncryptTool.encrypt(activityId));
//            url.append("&id=");
//            if (!Objects.isNull(id)){
//                url.append(IntegerEncryptTool.encrypt(id));
//            }
//            response.setData(url.toString());
//            //response = sourceEventService.getSourceEventPublishUrl(tid);
//        } catch (Exception e) {
//            logger.error("=========活动版本链接参数，activityId={},id={}", activityId, id, e);
//            response.setMsg("活动版本链接参数!");
//            response.setStatus(CommonResponseCodeEnum.ERROR.getCode());
//        }
//        return response;
//    }
//
//    @PostMapping("/v100/preview")
//    @ApiOperation(value = "活动预览", notes = "活动预览")
//    public BusiSystemResponse<SourceEventPreviewBO> createVersionUrl(@RequestParam(value = "tid") String tid) {
//        logger.info("=========活动预览参数，tid={}", tid);
//        BusiSystemResponse<SourceEventPreviewBO> response = new BusiSystemResponse<>();
//        try {
//            response = sourceEventVersionService.sourceEventPublishPreview(tid);
//        } catch (Exception e) {
//            logger.error("=========活动预览参数，tid={}", tid);
//            response.setMsg("活动预览参数!");
//            response.setStatus(CommonResponseCodeEnum.ERROR.getCode());
//        }
//        return response;
//    }
//
//    @PostMapping("/v100/saveVersion")
//    @ApiOperation(value = "活动版本保存", notes = "活动版本保存")
//    public BusiSystemResponse<SourceEventVersionBO> saveVersion(@RequestBody @Valid SourceEventVersionBO vo) {
//        logger.info("=========活动版本保存,vo={}", JsonUtil.toJSON(vo));
//        BusiSystemResponse<SourceEventVersionBO> response = new BusiSystemResponse<>();
//        try {
//            return sourceEventVersionService.saveSourceEventVersion(vo);
//        } catch (Exception e) {
//            logger.error("=========活动版本保存失败，vo={}", JsonUtil.toJSON(vo), e);
//            response.setMsg("活动版本保存!");
//            response.setStatus(CommonResponseCodeEnum.ERROR.getCode());
//        }
//        return response;
//    }
//
//    @PostMapping("/v100/listVersion")
//    @ApiOperation(value = "活动版本列表", notes = "活动版本列表")
//    public BusiSystemResponse<List<SourceEventVersionManageBO>> listVersion(@RequestBody @Valid SourceEventVersionBO vo) {
//        logger.info("=========活动版本列表,vo={}", JsonUtil.toJSON(vo));
//        BusiSystemResponse<List<SourceEventVersionManageBO>> response = new BusiSystemResponse<>();
//        try {
//            BusiSystemResponse<List<SourceEventVersionManageBO>> version = sourceEventVersionService.listSourceEventVersion(BeanUtil.copyProperties(vo, SourceEventVersionBO.class));
//            version.getData().forEach(e->{
//                e.setEncryptId(IntegerEncryptTool.encrypt(e.getId()));
//            });
//            return version;
//        } catch (Exception e) {
//            logger.error("=========活动版本列表查询失败，vo={}", JsonUtil.toJSON(vo), e);
//            response.setMsg("活动版本列表查询失败!");
//            response.setStatus(CommonResponseCodeEnum.ERROR.getCode());
//        }
//        return response;
//    }
//
//    @PostMapping("/v100/publishVersion")
//    @ApiOperation(value = "活动版本发布/回滚", notes = "活动版本发布/回滚")
//    public BusiSystemResponse<Boolean> publishSourceEventVersion(@RequestBody @Valid SourceEventPublishParamBO vo) {
//        logger.info("=========活动版本发布/回滚,vo={}", JsonUtil.toJSON(vo));
//        BusiSystemResponse<Boolean> response = new BusiSystemResponse<>();
//        try {
//            return sourceEventVersionService.publishSourceEventVersion(BeanUtil.copyProperties(vo, SourceEventPublishParamBO.class));
//        } catch (Exception e) {
//            logger.error("=========活动版本发布/回滚失败，vo={}", JsonUtil.toJSON(vo), e);
//            response.setMsg("活动版本发布/回滚失败!");
//            response.setStatus(CommonResponseCodeEnum.ERROR.getCode());
//        }
//        return response;
//    }
//
//    @PostMapping("/v100/dersion")
//    @ApiOperation(value = "活动版本发布/回滚", notes = "活动版本发布/回滚")
//    public BusiSystemResponse<String> dersion(@RequestParam("url") String url) throws Exception {
//        logger.info("=========活动版本发布/回滚,vo={}", JsonUtil.toJSON(url));
//        BusiSystemResponse<String> ret = new BusiSystemResponse<>();
//        // 生成一个httpclient对象
//        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
//        HttpGet httpget =new HttpGet(url);
//        HttpResponse response = httpclient.execute(httpget);
//        HttpEntity entity = response.getEntity();
//        InputStream in = entity.getContent();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//        StringBuffer sbf = new StringBuffer();
//        try{
//            String reslut="";
//            while((reslut = reader.readLine()) != null) {
//                sbf.append(reslut);
//            }
//            reader.close();
//            ret.setData(sbf.toString());
//            return ret;
//        }catch (IOException e) {
//            e.printStackTrace();
//        } finally{
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        }
//        ret.setData(sbf.toString());
//        return ret;
//    }
//
//    @GetMapping("/v100/sourceInfo")
//    @ApiOperation(value = "活动版本链接", tags = "活动版本链接")
//    public BusiSystemResponse<SourceEventInfoBO> sourceInfo(
//            @RequestParam(value = "tid") String tid, @RequestParam(value = "id", required = false) String id) {
//        logger.info("=========活动版本链接参数,tid={},id={}", tid, id);
//        BusiSystemResponse<SourceEventInfoBO> response = new BusiSystemResponse<>();
//        try {
//            Integer dTid = 0, dId = 0;
//            if (StringUtil.isNotEmpty(tid)) {
//                dTid = IntegerEncryptTool.decrypt(tid);
//            }
//            if (StringUtil.isEmpty(tid) || dTid == 0) {
//                response.setMsg("活动主键为空");
//                response.setStatus(CommonResponseCodeEnum.ERROR.getCode());
//                return response;
//            }
//            if (StringUtil.isNotEmpty(id)) {
//                dId = IntegerEncryptTool.decrypt(id);
//            }
//            BusiSystemResponse<SourceEventInfoBO> info = sourceEventVersionService.getSourceEventPublishInfo(dTid, dId);
//            if (info.getData()!=null){
//                response.setData(BeanUtil.copyProperties(info.getData(),SourceEventInfoBO.class));
//                response.setStatus(CommonResponseCodeEnum.SUCCESS.getCode());
//            }
//        } catch (Exception e) {
//            logger.error("=========活动版本链接参数,tid={},id={}", tid, id, e);
//            response.setMsg("活动链接参数解析错误!");
//            response.setStatus(CommonResponseCodeEnum.ERROR.getCode());
//        }
//        return response;
//    }
//}
