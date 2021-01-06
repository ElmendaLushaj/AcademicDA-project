package com.example.demo.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int commentId;
    @Column(nullable=false)
    private String description;
    @Column(nullable=false)
    private String createdBy;
    @Column
    private Date creationD;

    @ManyToOne
    @JoinColumn(name="documentId")
    private Document document;

    public Comment(int commentId, String description, String createdBy, Date creationD, Document document) {
        this.commentId = commentId;
        this.description = description;
        this.createdBy = createdBy;
        this.creationD = creationD;
        this.document = document;
    }
    public Comment(){}

    public int getCommentId() {
        return commentId;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getCreationD() {
        return creationD;
    }

    public Document getDocument() {
        return document;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreationD(Date creationD) {
        this.creationD = creationD;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
