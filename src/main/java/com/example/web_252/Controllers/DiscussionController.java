package com.example.web_252.Controllers;

import com.example.web_252.Service.DiscussionService;
import com.example.web_252.Models.Discussion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/discussion")
public class DiscussionController {

    @Autowired
    private DiscussionService discussionService;


    // vraća sve diskusije
    @GetMapping
    public List<Discussion> getAllDiscussions() {
        return discussionService.getAllDiscussionsWithC();
    }

    // vraća diskusiju po idu
    @GetMapping("/{id}")
    public ResponseEntity<Discussion> getDiscussionById(@PathVariable Long id) {
        Optional<Discussion> discussion = discussionService.getById(id);
        return discussion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // dodavanje diskusije, admin only
    @PostMapping
    public ResponseEntity<?> createDiscussion(@RequestBody Discussion discussion) {
        try{
            discussionService.save(discussion);
        }catch(Error e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    //route na formu za dodavanje diskusije
    @GetMapping("/go-add")
    public String showAddDiscussionForm(Model model) {
        model.addAttribute("discussion", new Discussion());
        return "redirect:/admin/discussions-add";
    }
    //refresh diskusija
    @PostMapping("/add")
    public String reloadDiscussions(@ModelAttribute Discussion discussion) {
        discussionService.save(discussion);
        return "redirect:/admin/discussions";
    }

    // brisanje
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiscussion(@PathVariable Long id) {
        discussionService.deleteDiscussion(id);
        return ResponseEntity.ok().build();
    }
}
