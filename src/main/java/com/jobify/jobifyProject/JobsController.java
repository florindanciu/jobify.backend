package com.jobify.jobifyProject;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
public class JobsController {

    ConcurrentMap<String, Job> jobs = new ConcurrentHashMap<>();

    @GetMapping("/")
    public List<Job> getAllJobs(){
        return new ArrayList<Job>(jobs.values());
    }

    @GetMapping("/{id}")
    public Job getJob(@PathVariable String id){
        return jobs.get(id);
    }

    @PostMapping("/")
    public Job addJob(@RequestBody Job job){
        jobs.put(job.getId(),job);
        return job;
    }

}
