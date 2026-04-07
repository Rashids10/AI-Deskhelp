# AI-Deskhelp Wireframes

## Chat Screen (Main Interface)
```mermaid
flowchart TB
    subgraph CHAT["Chat Screen"]
        direction TB
        H["Header: title, user status, logout"]
        M["Messages: user bubbles, AI bubbles, escalation banner"]
        I["Input row: text box, send button, attachment optional"]
        F["Footer note: AI may escalate to human if unsure"]
        H --> M --> I
        F -.-> I
    end
```

## Ticket Escalation Screen
```mermaid
flowchart TB
    subgraph TICKET["Ticket Escalation"]
        direction TB
        TH["Header: ticket id and status"]
        SUM["Summary: user question and FAQ context"]
        TL["Timeline: user messages, AI attempts, escalation event"]
        ACT["Actions: add comment, upload file, mark resolved"]
        META["Meta: priority, assignee, SLA note"]
        TH --> SUM --> TL --> ACT
        META -.-> SUM
    end
```

## Admin / Support Dashboard (Optional)
```mermaid
flowchart TB
    subgraph ADMIN["Admin or Support Dashboard"]
        direction TB
        FIL["Filters: status, priority, assignee, search"]
        LIST["Ticket table: id, user, last message, status, age"]
        DETAIL["Ticket detail: timeline, context snippets, reply box"]
        QUICK["Quick actions: assign self, close ticket, canned response"]
        FIL --> LIST --> DETAIL
        QUICK -.-> DETAIL
    end
```

## Interaction Notes
- Chat: user types → send → message appears; if low confidence, show escalation banner with ticket link.
- Escalation: timeline shows AI attempts; agent comments or resolves via actions; status syncs back to chat.
- Admin: filter/search tickets; selecting a row opens detail; quick actions handle assignment/closure.

## Angular Component Structure (MVP)
- `AppComponent`
  - `NavbarComponent` (logo/user status)
  - `RouterOutlet`
- `ChatPageComponent`
  - `ChatHeaderComponent`
  - `MessageListComponent`
    - `MessageBubbleComponent` (user/ai variants)
    - `EscalationBannerComponent` (ticket link)
  - `ChatInputComponent`
- `TicketPageComponent`
  - `TicketHeaderComponent`
  - `TicketSummaryComponent`
  - `TicketTimelineComponent`
    - `TimelineItemComponent`
  - `TicketActionsComponent`
  - `TicketMetaComponent`
- `AdminDashboardComponent` (optional)
  - `TicketFiltersComponent`
  - `TicketTableComponent`
    - `TicketRowComponent`
  - `TicketDetailComponent`
    - shares `TicketTimelineComponent`, `TicketActionsComponent`
    - `ContextSnippetsComponent`
    - `ReplyBoxComponent`
