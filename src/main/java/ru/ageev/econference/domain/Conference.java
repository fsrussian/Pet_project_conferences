package ru.ageev.econference.domain;


import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Conference {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String conferenceName;
    private String text;
    private String fileName;
    @Temporal(TemporalType.DATE)
    private Date conferenceDate;
    public String dateget(){
        SimpleDateFormat dateFor = new SimpleDateFormat("dd/MM/yyyy");
        return dateFor.format(conferenceDate);
    }

    public Date getConferenceDate() {
        return conferenceDate;
    }

    public void setConferenceDate(Date conferenceDate) {
        this.conferenceDate = conferenceDate;
    }

    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "CONFERENCE_LISTENER", joinColumns = { @JoinColumn(name = "LISTENER_ID") }, inverseJoinColumns = { @JoinColumn(name = "CONFERENCE_ID") })
           // private List<User> listeners = new ArrayList<>();
    private Set<User> listeners = new HashSet<>(20);


    public boolean addListener(User user){
        if (user==null)
            return false;
        else{
            listeners.add(user);
            return true;
        }
    }

    public Set<User> getListeners() {
        return listeners;
    }

    public void setListeners(Set<User> listeners) {
        this.listeners = listeners;
    }
//
//    public List<User> getListeners() {
//        return listeners;
//    }
//
//    public void setListeners(List<User> listeners) {
//        this.listeners = listeners;
//    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    private String fullDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Conference(String text, String tag) {
        this.text = text;
        this.conferenceName = conferenceName;
    }

    public Conference(User author, String text, String conferenceName) {
        this.author = author;
        this.text = text;
        this.conferenceName = conferenceName;
    }

    public Conference( User author, String conferenceName, String text, String fullDescription, Date date) {
        this.conferenceName = conferenceName;
        this.text = text;
        this.fullDescription = fullDescription;
        this.author = author;
        this.conferenceDate = date;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getAuthorName(){
    return author != null ? author.getUsername(): "none";
}

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User user) {
        this.author = user;
    }



    public Conference() {
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String tag) {
        this.conferenceName = tag;
    }
}