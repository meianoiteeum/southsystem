package br.com.southsytem.batch.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class ListenerConfig extends JobExecutionListenerSupport {

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
