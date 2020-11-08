package platform;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
public class CodeSnippet {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String code;

    @Column
    private int time;

    @Column
    private int views;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final String date;

    @Column
    @JsonIgnore
    private LocalDateTime lastTimeCheck;

    @Column
    @JsonIgnore
    private boolean valueRestrictionApplied = false;


    public CodeSnippet() {
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.lastTimeCheck = LocalDateTime.now();
    }

    public CodeSnippet(String code, int time, int views) {
        this.code = code;
        this.time = time;
        this.views = views;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.lastTimeCheck = LocalDateTime.now();
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    @JsonIgnore
    public UUID getId() {
        return id;
    }


    public int getTime() {
        return time;
    }


    public int getViews() {
        return views;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCode(String code) {
        System.out.println("setCode");
        this.code = code;
    }

    public void setTime(int time) {
        System.out.println("setTime");
        this.time = time;
    }

    public void setViews(int views) {
        System.out.println("setViews " + views);
        if (!valueRestrictionApplied && views > 0) {
            valueRestrictionApplied = true;
        }
        this.views = views;
    }

    public LocalDateTime getLastTimeCheck() {
        return lastTimeCheck;
    }

    public void setLastTimeCheck(LocalDateTime lastTimeCheck) {
        this.lastTimeCheck = lastTimeCheck;
    }

    public boolean isValueRestrictionApplied() {
        return valueRestrictionApplied;
    }

    public void setValueRestrictionApplied(boolean valueRestrictionApplied) {
        this.valueRestrictionApplied = valueRestrictionApplied;
    }
}
