package org.example.warehouse.service;

import org.example.warehouse.dto.AttachmentDto;
import org.example.warehouse.model.Attachment;
import org.example.warehouse.model.Result;
import org.example.warehouse.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;

    public List<Attachment> getAttachments() {
        return attachmentRepository.findAll();
    }

    public Optional<Attachment> getAttachmentById(Integer id) {
        return attachmentRepository.findById(id);
    }

    public Result addAttachment(AttachmentDto attachmentDtp) {
        Attachment attachment = new Attachment();
        attachment.setName(attachmentDtp.getName());
        attachment.setSize(attachmentDtp.getSize());
        attachment.setContentType(attachmentDtp.getContentType());
        attachmentRepository.save(attachment);
        return new Result(true,"Attachment added successfully");
    }

    public Result updateAttachment(AttachmentDto attachmentDto,Integer id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            attachment.setName(attachmentDto.getName());
            attachment.setSize(attachmentDto.getSize());
            attachment.setContentType(attachmentDto.getContentType());
            attachmentRepository.save(attachment);
            return new Result(true,"Attachment updated successfully");
        }
        return new Result(false,"Attachment not found");
    }

    public Result deleteAttachment(Integer id) {
        attachmentRepository.deleteById(id);
        return new Result(true,"Attachment deleted successfully");
    }
}
