package com.jobify.jobifyProject;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("/api")
public class JobsController {

    ConcurrentMap<String, Job> jobs = new ConcurrentHashMap<>();

    @GetMapping("/")
    public List<Job> getAllJobs(){
        return new ArrayList<Job>(jobs.values());
    }

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Finds a job by id",
            notes = "Provide an id to search for a specific job",
            response = Job.class)
    public Job getJob(@PathVariable String id){
        return jobs.get(id);
    }

    @PostMapping("/")
    public Job addJob(@RequestBody Job job){
        jobs.put(job.getId(),job);
        return job;
    }

}
