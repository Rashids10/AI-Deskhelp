package com.rashid.ai_helpdesk.service;
public class Ticket {
    private Long ticketId;
    private String title;
    private Long createdBy;
    private String comment;



public Ticket(Long ticketId, String title, Long createdBy, String comment) {
    this.ticketId = ticketId;
    this.title = title;
    this.createdBy = createdBy;
    this.comment = comment;

}
}
