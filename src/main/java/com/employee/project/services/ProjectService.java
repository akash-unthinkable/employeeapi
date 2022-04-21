package com.employee.project.services;

import com.employee.project.dao.ProjectRepository;
import com.employee.project.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // get all Projects
    public List<Project> getAllProject(){

        return   projectRepository.findAll();
    }
    // get single projects


    public Optional<Project> getProjectById (int id) {

        Optional<Project> project  = Optional.ofNullable(projectRepository.findById(id));
        return project;
    }

    public Project addProject(Project project){
        Project projectResult;
        projectResult = projectRepository.save(project);
        return projectResult;
    }
    public void deleteProject(int bid){

        projectRepository.deleteById(bid);
    }
    //update project
    public void updateProject(Project project, int projectId){
        project.setPrId(projectId);
        projectRepository.save(project);
    }
    public  List<Project> findAllSortingAscn(int page){
        Pageable sortedAsc= PageRequest.of(page,3, Sort.by("title"));

        List<Project> allProject=  projectRepository.findAll(sortedAsc).getContent();
        return allProject;
    }


    public  List<Project> findAllSortingDesc(int page){
        Pageable sortedDesc= PageRequest.of(page,3, Sort.by("title").descending());

        List<Project> allProject=  projectRepository.findAll(sortedDesc).getContent();
        return allProject;
    }

}
