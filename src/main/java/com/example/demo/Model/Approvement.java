package com.example.demo.Model;

import javax.persistence.*;

@Entity
public class Approvement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int approvementId;
    @Column(nullable=false)
    private String approvedBy;

    @Column(nullable=false)
    private boolean approveRefuse;

   /* @ManyToOne
    @JoinColumn(name="documentId")
    private Document document;
*/

    @OneToOne(mappedBy = "approve")
    private Document document;

    public Approvement(int approvementId, String approvedBy, boolean approveRefuse, Document document) {
        this.approvementId = approvementId;
        this.approvedBy = approvedBy;
        this.approveRefuse = approveRefuse;
        this.document = document;
    }

    public Approvement(){}

    public int getApprovementId() {
        return approvementId;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public boolean isApproveRefuse() {
        return approveRefuse;
    }

    public Document getDocument() {
        return document;
    }

    public void setApprovementId(int approvementId) {
        this.approvementId = approvementId;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public void setApproveRefuse(boolean approveRefuse) {
        this.approveRefuse = approveRefuse;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
