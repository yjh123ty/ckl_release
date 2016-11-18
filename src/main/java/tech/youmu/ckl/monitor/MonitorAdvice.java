package tech.youmu.ckl.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import tech.youmu.ckl.app.vo.RemoteResult;
import tech.youmu.ckl.constants.ResultStatus;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.exception.ParamException;
import tech.youmu.ckl.exception.TokenExecption;
import tech.youmu.ckl.utils.ResultUtils;

/**
 * 拦截所有RestController异常
 * @author zhaojun 2015年11月20日 下午2:21:48
 */
@ControllerAdvice(annotations = RestController.class)
public class MonitorAdvice {
    public static final Logger logger = LoggerFactory.getLogger(MonitorAdvice.class);

    @ExceptionHandler
    @ResponseBody
    public RemoteResult handleException(Exception ex) {

        logger.error(ex.getMessage(), ex);
        if (ex instanceof MissingServletRequestParameterException) {
            return ResultUtils.createParamErrorResult(ex.getMessage());
        }
        if (ex instanceof ConversionNotSupportedException) {
            return ResultUtils.createSysErrorResult(ex.getMessage());
        }
        if (ex instanceof HttpMediaTypeNotAcceptableException) {
            return ResultUtils.createSysErrorResult(ex.getMessage());
        }
        if (ex instanceof TypeMismatchException) {
            return ResultUtils.createSysErrorResult(ex.getMessage());
        }
        if (ex instanceof NoHandlerFoundException) {
            return ResultUtils.createSysErrorResult(ex.getMessage());
        }
        if (ex instanceof TokenExecption) {
            return ResultUtils.createTokenErrorResult(ex.getMessage());
        }
        if (ex instanceof BizExecption) {
            return ResultUtils.createBizErrorResult(ex.getMessage());
        }
        if (ex instanceof ParamException) {
            return ResultUtils.createParamErrorResult(ex.getMessage());
        }
        if (ex instanceof RuntimeException) {
            return ResultUtils.createExceptionResult(ex.getMessage());
        }
        return ResultUtils.createSysErrorResult(ResultStatus.SYSTEM_ERROR_MSG);
    }

}
