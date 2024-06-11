package org.example.warehouse.controller;

import org.example.warehouse.dto.AttachmentDto;
import org.example.warehouse.model.Attachment;
import org.example.warehouse.model.Result;
import org.example.warehouse.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Attachment")

public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @GetMapping()
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public List<Attachment> getAttachments() {
        return attachmentService.getAttachments();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public Optional<Attachment> AttachmentGetById(@PathVariable Integer id) {
        return attachmentService.getAttachmentById(id);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Result addAttachment(@RequestBody AttachmentDto attachmentDto) {
        return attachmentService.addAttachment(attachmentDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Result updateAttachment(@RequestBody AttachmentDto attachmentDto, @PathVariable Integer id) {
        return attachmentService.updateAttachment(attachmentDto, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','USER')")
    public Result deleteAttachment(@PathVariable Integer id) {
        return attachmentService.deleteAttachment(id);
    }
}
