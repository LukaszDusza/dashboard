package com.dfsp.dashboard.controllers;


import com.dfsp.dashboard.repositories.PageFormSelectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.dfsp.dashboard.entities.PageFormSelector;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/snapshot")
public class SnapshotController {

    @Autowired
    PageFormSelectorRepository pageFormSelectorRepository;

    @GetMapping("/form/all")
    public List<PageFormSelector> getForms() {
        return pageFormSelectorRepository.findAll();
    }

    @GetMapping("/form/{id}")
    public PageFormSelector getFormById(@PathVariable("id") Long id) {
        Optional<PageFormSelector> optionalPageFormSelector = pageFormSelectorRepository.findById(id);
        return optionalPageFormSelector.get();
    }

    @PostMapping("/form/add")
    public PageFormSelector addForm(@ModelAttribute PageFormSelector pageFormSelector) {
        PageFormSelector pfs = new PageFormSelector(
                pageFormSelector.getTitle(),
                pageFormSelector.getRouterLink()
        );
        return pageFormSelectorRepository.save(pfs);
    }

    @DeleteMapping("/form/delete/{id}")
    public String deleteForm(@PathVariable("id") Long id) {
        pageFormSelectorRepository.deleteById(id);
        return "form id: " + id + " deleted!";
    }

    @PutMapping("form/update/{id}")
    public String updateForm(@ModelAttribute PageFormSelector pageFormSelector, @PathVariable Long id) {
        Optional<PageFormSelector> resultOptional = pageFormSelectorRepository.findById(id);
        resultOptional.ifPresent(result -> {
            result.setTitle(pageFormSelector.getTitle());
            result.setRouterLink(pageFormSelector.getRouterLink());
            pageFormSelectorRepository.save(result);
        });
        return "form id: " + id + " updated!";
    }
}
