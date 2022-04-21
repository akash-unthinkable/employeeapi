package com.employee.project.controllers;
import com.employee.project.entity.Project;
import com.employee.project.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

    @RequestMapping("/api2/v1")
    @RestController
    public class ProjectController {

        @Autowired
        private ProjectService projectService;

        @GetMapping("/projects")
        public ResponseEntity<List<Project>> getProjects(){

            List<Project> list=projectService.getAllProject();
            if(list.size()<=0)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return ResponseEntity.status(HttpStatus.CREATED).body(list);
        }

        @GetMapping("/projects/desc/{page}")
        public ResponseEntity<List<Project>> getAllSortedAscending(@PathVariable("page") int page) {

            List<Project> list= projectService.findAllSortingDesc(page);
            if(list.size()<=0)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return ResponseEntity.status(HttpStatus.CREATED).body(list);
        }
        @GetMapping("/projects/ascn/{page}")
        public ResponseEntity<List<Project>> getAllSortedProject(@PathVariable("page") int page) {

            List<Project> list= projectService.findAllSortingAscn(page);
            if(list.size()<=0)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return ResponseEntity.status(HttpStatus.CREATED).body(list);
        }
        @GetMapping("/project/{id}")
        public ResponseEntity<Project> getProject(@PathVariable("id") int id ){

            Optional<Project> project= projectService.getProjectById(id);
            if (!project.isPresent())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return ResponseEntity.of(project);
        }
        // add new proj
        @PostMapping("/project")
        public ResponseEntity<Project> addProject(@RequestBody Project project){
            Project b= null;
            try {
                b = this.projectService.addProject(project);
                return ResponseEntity.of(Optional.of(b));
            }catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        // delete project
        @DeleteMapping("/project/{projId}")
        public ResponseEntity<Void> deleteProject(@PathVariable("projId") int projId){
            try {
                this.projectService.deleteProject(projId);
                return   ResponseEntity.ok().build();
            } catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

        }
        //update
        //project
        @PutMapping("/project/{projectid}")
        public ResponseEntity<Project> updateProject(@RequestBody Project project, @PathVariable("projectid") int prid){
            try {
                this.projectService.updateProject(project, prid);
                return ResponseEntity.ok().build();
            }
            catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

    }
