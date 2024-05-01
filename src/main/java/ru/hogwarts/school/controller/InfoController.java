package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.LongStream;
import java.util.stream.Stream;


public class InfoController {

    @Value("${server.port}")
    private int serverPort;

    @GetMapping("/port")
    public int getPortNumber(){
        return serverPort;
    }
    @GetMapping("/sum")
    public int getSum(){
        long time = System.currentTimeMillis();
        LongStream.rangeClosed(1, 1_000_000)
                .parallel()
                .reduce(0,Long::sum);

        long timeFinish = System.currentTimeMillis() - time;
        System.out.printf("time " , timeFinish);
        return (int) timeFinish;
    }
}
