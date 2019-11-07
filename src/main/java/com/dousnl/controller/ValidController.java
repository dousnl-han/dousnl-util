package com.dousnl.controller;

import com.dousnl.domain.AdviceCanel;
import com.dousnl.utils.response.Resp;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/10/23 15:11
 */
@RestController
@RequestMapping("/valid")
public class ValidController {

    /**
     * 车主取消订单接口
     * @return
     */
    @ApiOperation(value = "车主取消订单接口", notes = "车主取消订单接口")
    @PostMapping(value = "/cancel")
    public Resp tmsPush(@Valid @Validated @RequestBody AdviceCanel canel, BindingResult bindingResult) {
        Resp resp = Resp.success();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            return Resp.failed(fieldError.getField() + ":" + fieldError.getDefaultMessage());
        }
//        try{
//            //JsonMsgBean msgBean = _adviceService.carOwnerCancelAdviceNo(canel);
//            if (!msgBean.getSuccess()) {
//                return Resp.failed((String) msgBean.getData());
//            }
//        } catch (PushException e) {
//            logger.error(e.getMessage(), e);
//            return Resp.failed(e.getMessage());
//        } catch (Exception e) {
//            logger.error("维修订单终止失败...", e);
//            return Resp.failed("维修订单终止失败...");
//        }
        return resp;
    }
    @ApiOperation(value = "车主取消订单接口", notes = "车主取消订单接口")
    @GetMapping(value = "/cancel1")
    public Resp tmsPushCancel(AdviceCanel canel, BindingResult bindingResult) {
        Resp resp = Resp.success();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            return Resp.failed(fieldError.getField() + ":" + fieldError.getDefaultMessage());
        }
//        try{
//            //JsonMsgBean msgBean = _adviceService.carOwnerCancelAdviceNo(canel);
//            if (!msgBean.getSuccess()) {
//                return Resp.failed((String) msgBean.getData());
//            }
//        } catch (PushException e) {
//            logger.error(e.getMessage(), e);
//            return Resp.failed(e.getMessage());
//        } catch (Exception e) {
//            logger.error("维修订单终止失败...", e);
//            return Resp.failed("维修订单终止失败...");
//        }
        return resp;
    }
}
