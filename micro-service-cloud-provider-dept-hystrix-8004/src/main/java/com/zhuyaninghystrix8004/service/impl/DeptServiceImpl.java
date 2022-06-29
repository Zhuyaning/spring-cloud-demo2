package com.zhuyaninghystrix8004.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhuyaning.entity.utils.CommonUtil;
import com.zhuyaninghystrix8004.service.DeptService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service("deptService")
public class DeptServiceImpl implements DeptService {

    @Override
    public String deptInfoOk(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "  deptInfo_Ok,id:   " + id;
    }

    //一旦该方法失败并抛出了异常信息后，会自动调用  @HystrixCommand 注解标注的 fallbackMethod 指定的方法
    //规定 5 秒钟以内就不报错，正常运行，超过 5 秒就报错，调用指定的方法
    @HystrixCommand(fallbackMethod = "deptTimeoutHandler", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")})
    @Override
    public String deptInfoTimeout(Integer id) {
        int outTime = 6;
        try {
            TimeUnit.SECONDS.sleep(outTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return "线程池：" + Thread.currentThread().getName() + "  deptInfo_Timeout,id:   " + id + "  耗时: " + outTime;
    }

    // 当服务出现故障后，调用该方法给出友好提示
    public String deptTimeoutHandler(Integer id) {
        return "系统繁忙请稍后再试！" + "线程池：" + Thread.currentThread().getName() + "  deptInfo_Timeout,id:   " + id;
    }

        /*
            metrics.rollingStats.timeInMilliseconds	    统计时间窗。
            circuitBreaker.sleepWindowInMilliseconds	休眠时间窗，熔断开启状态持续一段时间后，熔断器会自动进入半熔断状态，这段时间就被称为休眠窗口期。
            circuitBreaker.requestVolumeThreshold	    请求总数阀值。

            在统计时间窗内，请求总数必须到达一定的数量级，Hystrix 才可能会将熔断器打开进入熔断开启转态，而这个请求数量级就是 请求总数阀值。Hystrix 请求总数阈值默认为 20，这就意味着在统计时间窗内，如果服务调用次数不足 20 次，即使所有的请求都调用出错，熔断器也不会打开。
            circuitBreaker.errorThresholdPercentage	错误百分比阈值。

            当请求总数在统计时间窗内超过了请求总数阀值，且请求调用出错率超过一定的比例，熔断器才会打开进入熔断开启转态，而这个比例就是错误百分比阈值。错误百分比阈值设置为 50，就表示错误百分比为 50%，如果服务发生了 30 次调用，其中有 15 次发生了错误，即超过了 50% 的错误百分比，这时候将熔断器就会打开。
        */

    //Hystrix 熔断案例
    @Override
    @HystrixCommand(fallbackMethod = "deptCircuitBreakerFallback", commandProperties = {
            //以下参数在 HystrixCommandProperties 类中有默认配置
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //是否开启熔断器
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1000"), //统计时间窗
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //统计时间窗内请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //休眠时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //在统计时间窗口期以内，请求失败率达到 60% 时进入熔断状态
    })
    public String deptCircuitBreaker(Integer id) {
        if (id < 0) {
            //当传入的 id 为负数时，抛出异常，调用降级方法
            throw new RuntimeException("提醒您，id 不能是负数！");
        }
        long serialNum = CommonUtil.getSnowFlakeString();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号为：" + serialNum;
    }

    //deptCircuitBreaker 的降级方法
    public String deptCircuitBreakerFallback(Integer id) {
        return "提醒您，id 不能是负数,请稍后重试!\t id:" + id;
    }
}
