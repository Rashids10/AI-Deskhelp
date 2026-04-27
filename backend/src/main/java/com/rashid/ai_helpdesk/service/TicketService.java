package com.rashid.ai_helpdesk.service;

public class TicketService {


    public Ticket createTicket(String quesition, Long ticketId, String title, Long createdBy, String comment) {
       Ticket ticket = new Ticket(ticketId, title, createdBy, comment);
       return ticket;
    }



    public Ticket gTicket(Long ticketId){
        return null;
    }

    public Ticket findAnswer(Long ticketId){
        /*
        hier wird RAG AUF gerufen und die Antwort zurückgegeben und wenn nciht dann wird ein ticket erstellt
        r
    }
         */
        return null;
    }
}
