package com.dtdhehe.common.vo;

import lombok.Data;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/5/7 14:38
 * @description
 **/
@Data
public class ResultVO {

    private static final Integer SUCCESS = 200;
    private static final Integer FAILED = 500;
    private static final Integer UNAUTH = 401;

    private Integer code;
    private String msg;
    private Object data;

    /**
     * 返回成功，带消息及数据
     * @param msg
     * @param object
     * @return
     */
    public static ResultVO success(String msg, Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(SUCCESS);
        resultVO.setMsg(msg);
        resultVO.setData(object);
        return resultVO;
    }

    /**
     * 返回成功，带消息
     * @param msg
     * @return
     */
    public static ResultVO success(String msg){
        return success(msg, null);
    }

    /**
     * 返回成功
     * @return
     */
    public static ResultVO success(){
        return success(null, null);
    }

    /**
     * 返回失败
     * @param msg
     * @return
     */
    public static ResultVO failed(String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(FAILED);
        resultVO.setMsg(msg);
        return resultVO;
    }

    /**
     * 没有权限
     * @param msg
     * @return
     */
    public static ResultVO unauth(String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(UNAUTH);
        resultVO.setMsg(msg);
        return resultVO;
    }

}
