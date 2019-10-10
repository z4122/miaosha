package org.seckill.exception;


/**
 * 运行期异常，重复秒杀异常。spring只会接受运行其异常
 */
public class RepeatKillException extends SeckillException{

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
