package com.taomz.mini.apps.util.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.taomz.mini.apps.util.enums.ApiCallResult;
import com.taomz.mini.apps.util.exception.ExceptionWrapper;
import com.taomz.sha.util.exception.IllegalParamException;
import com.taomz.sha.util.exception.ParamAbsentException;
import com.taomz.sha.util.response.BaseResponseModel;
import com.taomz.sha.util.response.BaseResponseModelForTaomz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description: 捕获全局异常信息
 * @author huawuque@taomz.com
 * @date 2020-06-17 11:24
 * 
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public BaseResponseModelForTaomz defaultErrorHandler(Exception e) {
        LOGGER.error("", e);
        BaseResponseModelForTaomz response = new BaseResponseModelForTaomz(ApiCallResult.EXCEPTION.getCode(),
                ApiCallResult.EXCEPTION.getDesc());
        return response;
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public BaseResponseModel IllegalArgumentExceptionHandler(IllegalArgumentException e) {
        BaseResponseModel response = new BaseResponseModel(ApiCallResult.OPTIONFAILED.getCode(), e.getMessage());
        return response;
    }

    @ExceptionHandler(value = SQLException.class)
    public BaseResponseModelForTaomz sqlErrorHandler(SQLException e) {
        LOGGER.error("", e);
        BaseResponseModelForTaomz response = new BaseResponseModelForTaomz(ApiCallResult.DBERROR.getCode(),
                ApiCallResult.DBERROR.getDesc());
        return response;
    }

    @ExceptionHandler(value = RestClientException.class)
    public BaseResponseModelForTaomz restClientErrorHandler(RestClientException e) {
        LOGGER.error("", e);
        BaseResponseModelForTaomz response = new BaseResponseModelForTaomz(ApiCallResult.RESTCLIENTERROR.getCode(),
                ApiCallResult.RESTCLIENTERROR.getDesc());
        return response;
    }

    @ExceptionHandler(value = ExceptionWrapper.class)
    public BaseResponseModelForTaomz customErrorHandler(ExceptionWrapper e) {
        BaseResponseModelForTaomz response = new BaseResponseModelForTaomz(e.getCode(), e.getCustomErrMsg());
        return response;
    }

    @ExceptionHandler(value = { IllegalParamException.class, ParamAbsentException.class,
            MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class })
    public BaseResponseModelForTaomz paramValidErrorHandler(Exception e) {
        BaseResponseModelForTaomz response = new BaseResponseModelForTaomz();
        if (e instanceof ParamAbsentException || e instanceof MissingServletRequestParameterException) {
            if (e instanceof ParamAbsentException) {
                response.setCode(ApiCallResult.EMPTY.getCode()).setMessage(String.format("%s : %s",
                        ApiCallResult.EMPTY.getDesc(), ((ParamAbsentException) e).getFaildField()));
            } else {
                response.setCode(ApiCallResult.EMPTY.getCode()).setMessage(ApiCallResult.EMPTY.getDesc());
            }
        } else if (e instanceof HttpMessageNotReadableException) {
            if (e.getCause() instanceof JsonProcessingException) {
                response.setCode(ApiCallResult.UNSUPPORTED.getCode()).setMessage(
                        "json parameter parse failed,please check your json format:  " + e.getCause().getMessage());
            } else {
                response.setCode(ApiCallResult.EXCEPTION.getCode()).setMessage(ApiCallResult.EXCEPTION.getDesc());
                LOGGER.error("", e);
                return response;
            }
        } else {
            response.setCode(ApiCallResult.UNSUPPORTED.getCode()).setMessage(e.getMessage());
        }
        LOGGER.info(response.getMessage());
        return response;
    }

    @ExceptionHandler(value = org.springframework.validation.BindException.class)
    public BaseResponseModelForTaomz validationBindExcepitonHandler(org.springframework.validation.BindException e) {
        FieldError fe = e.getFieldError();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(fe.getDefaultMessage(), e);
        }
        return new BaseResponseModelForTaomz().setCode(ApiCallResult.ILLEGAL_ARGUMENT.getCode())
                .setMessage(fe.getDefaultMessage());
    }

    @ExceptionHandler(value = org.springframework.web.bind.MethodArgumentNotValidException.class)
    public BaseResponseModelForTaomz methodArgumentNotValidException(
            org.springframework.web.bind.MethodArgumentNotValidException e) {
        FieldError fe = e.getBindingResult().getFieldError();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(fe.getDefaultMessage(), e);
        }
        return new BaseResponseModelForTaomz().setCode(ApiCallResult.ILLEGAL_ARGUMENT.getCode())
                .setMessage(fe.getDefaultMessage());
    }

    @ExceptionHandler(value = com.tencentcloudapi.common.exception.TencentCloudSDKException.class)
    public BaseResponseModelForTaomz tencentCloudSDKExceptionHandler(com.tencentcloudapi.common.exception.TencentCloudSDKException e) {
        return new BaseResponseModelForTaomz().setCode(ApiCallResult.RESTCLIENTERROR.getCode())
                .setMessage(e.getMessage());
    }
}
