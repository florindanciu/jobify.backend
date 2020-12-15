package com.jobify.jobifyProject.JobsMock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;

    public Job addJob(Job job){
        jobRepository.save(job);
        return job;
    }

}
