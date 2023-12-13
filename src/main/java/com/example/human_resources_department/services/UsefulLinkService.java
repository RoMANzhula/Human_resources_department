package com.example.human_resources_department.services;

import com.example.human_resources_department.models.UsefulLink;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.repositories.UsefulLinkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsefulLinkService {


    private final UsefulLinkRepository usefulLinkRepository;

    public UsefulLinkService(UsefulLinkRepository usefulLinkRepository) {
        this.usefulLinkRepository = usefulLinkRepository;
    }


    @Transactional(readOnly = true)
    public List<UsefulLink> findAllLinksByOwner(Long ownerId) {
        return usefulLinkRepository.findByOwnerId(ownerId);
    }

    @Transactional
    public void addNewUsefulLink(User currentUser, UsefulLink usefulLink) {
            usefulLink.setOwner(currentUser);

            usefulLinkRepository.save(usefulLink);
    }
}
