package org.seckill.exception;

/**
 * 秒杀结束异常，不应该再去执行秒杀了
 */
public class SeckillCloseException extends SeckillException{
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
